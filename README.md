# Credit_Fraud_Detection_App

Credit fraud detection app alerts when it detects a change in the transaction operator's location,
If There is a change in user country, the system will notify the user of the exact time the transaction was made.

The system uses an API call to the site https://ipstack.com,
to get more information about the IP address from which the transaction was made,
and it saves the information it received from the site in Redis in order to quickly access IP information and prevent unnecessary API requests to https://ipstack.com.
