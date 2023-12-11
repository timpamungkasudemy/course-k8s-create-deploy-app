import os
import psycopg2
from psycopg2 import extras
from flask import Flask, jsonify
from datetime import datetime
from payment_tax_rate_data import create_table_tax_rates, insert_tax_rates_data

app = Flask(__name__)

db_config = {
    'dbname': os.getenv('PAYMENT_DATABASE_NAME', 'postgres'),
    'user': os.getenv('PAYMENT_DATABASE_USERNAME', 'postgres'),
    'password': os.getenv('PAYMENT_DATABASE_PASSWORD', 'mysecretpassword'),
    'host': os.getenv('PAYMENT_DATABASE_HOST', 'localhost'),
    'port': int(os.getenv('PAYMENT_DATABASE_PORT', 5432)),
}

def create_db_connection():
    connection = psycopg2.connect(**db_config)
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
    cursor = connection.cursor(cursor_factory=psycopg2.extras.RealDictCursor)

    try:
        cursor.execute("SELECT invoice_amount_min, invoice_amount_max, tax_rate FROM tax_rates")
        tax_rates_data = cursor.fetchall()

        return jsonify({"tax_rates": tax_rates_data})
    except Exception as e:
        return jsonify({"error": str(e)})
    finally:
        cursor.close()
        connection.close()
