CREATE TABLE IF NOT EXISTS chart_of_accounts (
	coa_id uuid NOT NULL,
	company varchar(255),
	cost_center varchar(255),
	natural_account varchar(255),
PRIMARY KEY (coa_id));