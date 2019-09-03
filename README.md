# ReservationsV1
Tables reservations


Example message to create a table:
{
	"tableNumber": 1,
	"numberOfSeats": 3
}


Example message to create reservation for a single table:
{
  "tableNumber": "1",
  "idClient": 546461546,
  "reservationDateFrom": "2019-09-02 08:00:00",
  "reservationDateTo": "2019-09-02 10:30:00"
}

Example message to create reservation for joined tables:
{
  "tableNumber": "2,3",
  "idClient": 546461546,
  "reservationDateFrom": "2019-09-02 08:00:00",
  "reservationDateTo": "2019-09-02 10:30:00"
}
