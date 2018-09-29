Running our program:

Please read the following system set up carefully.
Then proceed to write in arguments into the events.txt file provided, in the format specified below.
Then run our program.

== Our System ==

Subway Stations

- ossington
	entry cardmachine ids
		- 1000
	exit cardmachine ids
		- 1001

- christie
	entry cardmachine ids
		- 1002
	exit cardmachine ids
		- 1003

- bathurst
	entry cardmachine ids
		- 1004
	exit cardmachine ids
		- 1005

- spadina
	entry cardmachine ids
		- 1006
	exit cardmachine ids
		- 1007

- st. george
	entry cardmachine ids
		- 1008
	exit cardmachine ids
		- 1009


Bus Stations

- a
	entry cardmachine ids
		- 1010
	exit cardmachine ids
		- 1011

- b
	entry cardmachine ids
		- 1012
	exit cardmachine ids
		- 1013

- c
	entry cardmachine ids
		- 1014
	exit cardmachine ids
		- 1015

- d
	entry cardmachine ids
		- 1016
	exit cardmachine ids
		- 1017

Users

- name: HAL, email: HAL@mail.com
	- card ID: 0
- dave: Dave, Dave@mail.com
	- card ID: 1

Admin Users

- name: Sai, email: sai@sai.com

================================

Events must be inputted into the text file in a specific format, as follows.

---

Enter station - enter; card id; cardmachine id

Exit station - exit; card id; cardmachine id

Add User - addUser; userName; userEmail

User add card - addNewCard; userEmail

User remove card - removeCard; cardID; userEmail

User add balance to card - addBalance; cardID; amount

User change name - changeName; userEmail; newName

User view recent trips - viewRecentTrips; userEmail

Admin user views report - adminView; adminEmail;

Exit program - exitProgram;


== Lines in Events.txt ==

For e.g, if HAL was to enter christie, you would have the following line in events.txt:

enter; 0; 1000

This may seem slightly cumbersome but the way the system is currently set up, the user's card would tap onto a
card machine at christie station.


== Handling Errors ==

If a user double enters, or double exits, the user is charged $6. For e.g., if a user enters Christie,
forgets to tap off, and taps on, on a bus, they would be charged $6.

This is similar to current transit systems. In a case of malfunctioning machines, users can always contact
customer support for refunds!


=== End of Day ==

The program must be manually exited at the end of the day with the exit command. Upon exiting, a daily report of the
number of stations reached, and total fare collected is printed to screen.

