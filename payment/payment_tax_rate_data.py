import psycopg2
import os
from uuid import uuid4

db_config = {
    'dbname': os.getenv('PAYMENT_DATABASE_NAME', 'postgres'),
    'user': os.getenv('PAYMENT_DATABASE_USERNAME', 'postgres'),
    'password': os.getenv('PAYMENT_DATABASE_PASSWORD', 'mysecretpassword'),
    'host': os.getenv('PAYMENT_DATABASE_HOST', 'localhost'),
    'port': int(os.getenv('PAYMENT_DATABASE_PORT', 5432)),
}

tax_rates_data = [
    {'invoice_amount_min': 0, 'invoice_amount_max': 1000, 'tax_rate': 0.05},
    {'invoice_amount_min': 1001, 'invoice_amount_max': 5000, 'tax_rate': 0.1},
    {'invoice_amount_min': 5001, 'invoice_amount_max': 99999999, 'tax_rate': 0.15},
]

def create_table_tax_rates():
    connection = psycopg2.connect(**db_config)
    cursor = connection.cursor()

    try:
        cursor.execute("""
            CREATE TABLE IF NOT EXISTS tax_rates (
                tax_rate_id UUID NOT NULL PRIMARY KEY,
                invoice_amount_min DOUBLE PRECISION,
                invoice_amount_max DOUBLE PRECISION,
                tax_rate DOUBLE PRECISION
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
    connection = psycopg2.connect(**db_config)
    cursor = connection.cursor()

    try:
        cursor.execute("TRUNCATE TABLE tax_rates RESTART IDENTITY CASCADE")

        for data in tax_rates_data:
            tax_rate_id = str(uuid4())  
            invoice_amount_min = data.get('invoice_amount_min')
            invoice_amount_max = data.get('invoice_amount_max')
            tax_rate = data.get('tax_rate')

            cursor.execute("""
                INSERT INTO tax_rates (tax_rate_id, invoice_amount_min, invoice_amount_max, tax_rate)
                VALUES (%s, %s, %s, %s)
            """, (tax_rate_id, invoice_amount_min, invoice_amount_max, tax_rate))

        connection.commit()
        print("Data inserted successfully!")
    except Exception as e:
        print(f"Error inserting data: {str(e)}")
    finally:
        cursor.close()
        connection.close()