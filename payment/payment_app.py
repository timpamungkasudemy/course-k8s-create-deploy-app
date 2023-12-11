from payment_rest_api import app
from payment_tax_rate_data import create_table_tax_rates, insert_tax_rates_data

if __name__ == '__main__':
    create_table_tax_rates()
    insert_tax_rates_data()

    app.run(debug=False, host='0.0.0.0', port=8882)
