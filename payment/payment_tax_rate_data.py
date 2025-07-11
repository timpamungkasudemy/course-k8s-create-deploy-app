import duckdb
import os
from uuid import uuid4

# DuckDB database file path
db_path = os.getenv('PAYMENT_DATABASE_PATH', 'payment.duckdb')

tax_rates_data = [
    {'invoice_amount_min': 0, 'invoice_amount_max': 1000, 'tax_rate': 0.05},
    {'invoice_amount_min': 1001, 'invoice_amount_max': 5000, 'tax_rate': 0.1},
    {'invoice_amount_min': 5001, 'invoice_amount_max': 99999999, 'tax_rate': 0.15},
]

def create_table_tax_rates():
    connection = duckdb.connect(db_path)
    
    try:
        connection.execute("""
            CREATE TABLE IF NOT EXISTS tax_rates (
                tax_rate_id VARCHAR PRIMARY KEY,
                invoice_amount_min DOUBLE,
                invoice_amount_max DOUBLE,
                tax_rate DOUBLE
            )
        """)
        print("Table created successfully!")
    except Exception as e:
        print(f"Error creating table: {str(e)}")
    finally:
        connection.close()

def insert_tax_rates_data():
    connection = duckdb.connect(db_path)
    
    try:
        # Clear existing data
        connection.execute("DELETE FROM tax_rates")

        for data in tax_rates_data:
            tax_rate_id = str(uuid4())  
            invoice_amount_min = data.get('invoice_amount_min')
            invoice_amount_max = data.get('invoice_amount_max')
            tax_rate = data.get('tax_rate')

            connection.execute("""
                INSERT INTO tax_rates (tax_rate_id, invoice_amount_min, invoice_amount_max, tax_rate)
                VALUES (?, ?, ?, ?)
            """, (tax_rate_id, invoice_amount_min, invoice_amount_max, tax_rate))

        print("Data inserted successfully!")
    except Exception as e:
        print(f"Error inserting data: {str(e)}")
    finally:
        connection.close()