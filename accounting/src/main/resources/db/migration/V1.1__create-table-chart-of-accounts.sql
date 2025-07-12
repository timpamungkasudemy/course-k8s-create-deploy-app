CREATE TABLE IF NOT EXISTS chart_of_accounts (
	coa_id VARCHAR(255) NOT NULL,
	company VARCHAR(255),
	cost_center VARCHAR(255),
	natural_account VARCHAR(255),
PRIMARY KEY (coa_id));