import os
import sqlite3
from flask import Flask, jsonify
from datetime import datetime
from payment_tax_rate_data import create_table_tax_rates, insert_tax_rates_data

app = Flask(__name__)

# SQLite database file path (use 'payment.db' for persistent or ':memory:' for in-memory database)
db_path = os.getenv('PAYMENT_DATABASE_PATH', 'payment.db')

def create_db_connection():
    connection = sqlite3.connect(db_path)
    connection.row_factory = sqlite3.Row  # This enables column access by name
    return connection

@app.route('/api/current_time', methods=['GET'])
def get_current_time():
    current_time = datetime.now().strftime('%H:%M:%S')
    return jsonify({"current_time": current_time})

@app.route('/api/default_payment_method', methods=['GET'])
def get_payment_application():
    default_payment_method = os.getenv('SETTING_DEFAULT_PAYMENT_METHOD', 'UNDEFINED')
    message = f"In payment application, default payment method is {default_payment_method}"
    return jsonify({"message": message})

@app.route('/api/tax_rates', methods=['GET'])
def get_tax_rates():
    connection = create_db_connection()
    cursor = connection.cursor()

    try:
        cursor.execute("SELECT invoice_amount_min, invoice_amount_max, tax_rate FROM tax_rates")
        result = cursor.fetchall()
        
        # Convert rows to dictionaries
        tax_rates_data = []
        for row in result:
            tax_rates_data.append({
                'invoice_amount_min': row[0],
                'invoice_amount_max': row[1],
                'tax_rate': row[2]
            })

        return jsonify({"tax_rates": tax_rates_data})
    except Exception as e:
        return jsonify({"error": str(e)})
    finally:
        cursor.close()
        connection.close()
