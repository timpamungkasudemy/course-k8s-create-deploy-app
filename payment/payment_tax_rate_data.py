import sqlite3
import os
from uuid import uuid4

# SQLite database file path (use 'payment.db' for persistent or ':memory:' for in-memory database)
db_path = os.getenv('PAYMENT_DATABASE_PATH', 'payment.db')

tax_rates_data = [
    {'invoice_amount_min': 0, 'invoice_amount_max': 1000, 'tax_rate': 0.05},
    {'invoice_amount_min': 1001, 'invoice_amount_max': 5000, 'tax_rate': 0.1},
    {'invoice_amount_min': 5001, 'invoice_amount_max': 99999999, 'tax_rate': 0.15},
]

def create_table_tax_rates():
    connection = sqlite3.connect(db_path)
    cursor = connection.cursor()
    
    try:
        cursor.execute("""
            CREATE TABLE IF NOT EXISTS tax_rates (
                tax_rate_id TEXT PRIMARY KEY,
                invoice_amount_min REAL,
                invoice_amount_max REAL,
                tax_rate REAL
            )
        """)
        connection.commit()
        print("Table created successfully!")
    except Exception as e:
        print(f"Error creating table: {str(e)}")
    finally:
        cursor.close()
        connection.close()

def insert_tax_rates_data():
    connection = sqlite3.connect(db_path)
    cursor = connection.cursor()
    
    try:
        # Clear existing data
        cursor.execute("DELETE FROM tax_rates")

        for data in tax_rates_data:
            tax_rate_id = str(uuid4())  
            invoice_amount_min = data.get('invoice_amount_min')
            invoice_amount_max = data.get('invoice_amount_max')
            tax_rate = data.get('tax_rate')

            cursor.execute("""
                INSERT INTO tax_rates (tax_rate_id, invoice_amount_min, invoice_amount_max, tax_rate)
                VALUES (?, ?, ?, ?)
            """, (tax_rate_id, invoice_amount_min, invoice_amount_max, tax_rate))

        connection.commit()
        print("Data inserted successfully!")
    except Exception as e:
        print(f"Error inserting data: {str(e)}")
    finally:
        cursor.close()
        connection.close()