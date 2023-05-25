# task 1 - EV API

> General Prompt for building a simple API for managing electric vehicle (EV) charging stations. The API will allow users to find nearby charging stations, view station details, and reserve charging slots.
> 

## Requirements

>💡 Hint, consider adding input validation, error handling, and logging to make your API more robust and maintainable.
> It is also up to you to make the design and implement decisions for the reservation endpoints and models.

- implement the following endpoints
    
    ```text
    GET /stations: 
    	- Retrieve a list of nearby charging stations based on the user's location.
    GET /stations/{stationId}: 
    	- Get detailed information about a specific charging station.
    POST /stations/{stationId}/reservations: 
    	- Reserve a charging slot at a specific charging station.
    GET /stations/{stationId}/reservations/{reservationId}: 
    	- Retrieve the details of a reservation.
    DELETE /stations/{stationId}/reservations/{reservationId}: 
    	- Cancel an existing reservation.
    ```
- expected runtime per endpoint
    ```text
    GET    - all:    200ms max
    GET    - single: 60ms  max
    POST   - single: 200ms max
    DELETE - single: 200ms max
    ```
    
## Sample data
- NoSQL
```text
[
  {
    "code": 200,
    "message": "All stations",
    "body": [
      {
        "_id": "645ac69b68bba63c04e73ef9",
        "station_id": 1,
        "name": "GreenCharge Central",
        "address": "123 Main Street",
        "city": "Los Angeles",
        "state": "CA",
        "zip": "90001",
        "latitude": 34.052235,
        "longitude": -118.243683,
        "charging_slots": [
          {
            "slot_id": 1,
            "type": "Level 2",
            "status": "Available"
          },
          {
            "slot_id": 2,
            "type": "Level 2",
            "status": "Available"
          },
          {
            "slot_id": 3,
            "type": "DC Fast Charging",
            "status": "Occupied"
          }
        ]
      },
      {
        "_id": "645ac69b68bba63c04e73efa",
        "station_id": 2,
        "name": "SuperCharge Plaza",
        "address": "456 Market Street",
        "city": "Los Angeles",
        "state": "CA",
        "zip": "90017",
        "latitude": 34.040711,
        "longitude": -118.255882,
        "charging_slots": [
          {
            "slot_id": 1,
            "type": "Level 2",
            "status": "Available"
          },
          {
            "slot_id": 2,
            "type": "Level 2",
            "status": "Occupied"
          },
          {
            "slot_id": 3,
            "type": "DC Fast Charging",
            "status": "Available"
          }
        ]
      }
    ]
  }
]
```
- SQL
```sql
-- Charging Stations
INSERT INTO charging_stations (station_id, name, address, city, state, zip, latitude, longitude) VALUES
(1, 'GreenCharge Central', '123 Main Street', 'Los Angeles', 'CA', '90001', 34.052235, -118.243683),
(2, 'SuperCharge Plaza', '456 Market Street', 'Los Angeles', 'CA', '90017', 34.040711, -118.255882);

-- Charging Slots
INSERT INTO charging_slots (slot_id, station_id, type, status) VALUES
(1, 1, 'Level 2', 'Available'),
(2, 1, 'Level 2', 'Available'),
(3, 1, 'DC Fast Charging', 'Occupied'),
(1, 2, 'Level 2', 'Available'),
(2, 2, 'Level 2', 'Occupied'),
(3, 2, 'DC Fast Charging', 'Available');
```
