CREATE TABLE subscriptions
(
  id SERIAL PRIMARY KEY,
  state text NOT NULL CHECK (state IN ('trial', 'expired', 'active', 'cancelled')),
  created_at timestamptz NOT NULL DEFAULT current_timestamp
);
ALTER TABLE subscriptions
	ADD COLUMN sys_period tstzrange NOT NULL DEFAULT tstzrange(current_timestamp, null);
CREATE TABLE subscriptions_history (LIKE subscriptions);


INSERT INTO subscriptions (state, created_at) VALUES ('cancelled', '2015-01-05 12:00:00');
INSERT INTO subscriptions (state, created_at) VALUES ('active', '2015-01-10 12:00:00');
	
INSERT INTO subscriptions_history (id, state, created_at, sys_period)
  VALUES (1, 'trial', '2015-01-05 12:00:00',
    tstzrange('2015-01-05 12:00:00', '2015-01-15 15:00:00')
  );
INSERT INTO subscriptions_history (id, state, created_at, sys_period)
  VALUES (1, 'active', '2015-01-05 12:00:00',
    tstzrange('2015-01-15 15:00:00', (SELECT lower(sys_period) FROM subscriptions WHERE id = 1))
  );
INSERT INTO subscriptions_history (id, state, created_at, sys_period)
  VALUES (2, 'trial', '2015-01-10 12:00:00',
    tstzrange('2015-01-10 15:00:00', (SELECT lower(sys_period) FROM subscriptions WHERE id = 2))
  );

CREATE VIEW subscriptions_with_history AS
		SELECT * FROM subscriptions
	  UNION ALL
		SELECT * FROM subscriptions_history;
