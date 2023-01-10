# Credit Fraud Detection App

Credit fraud detection app alerts when it detects a change in the transaction operator's location,
If There is a change in user country, the system will notify the user of the exact time the transaction was made.






<img width="705" alt="image" src="https://user-images.githubusercontent.com/83716607/211530083-2fdd0b5b-785b-42c7-9b76-602e5eac8ccb.png">






The system uses an API call to the site https://ipstack.com,
to get more information about the IP address from which the transaction was made,
and it saves the information it received from the site in Redis in order to quickly access IP information and prevent unnecessary API requests to https://ipstack.com.
