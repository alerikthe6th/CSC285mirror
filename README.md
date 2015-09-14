Michael J. Currie, Joseph Godfrey, 
Scott Doberstein, Al Vi
Team Cormorant

Team Cormorant Use Case/User Story

Use Case: Mr. Sondahl receives an email order specifying ordering details which include the type of pottery, color details, an item quantity, and request date. Sondahl then runs the java program, which prompts a UI of a list of snapshot order details which can be sorted by name, date, or progression. Sondahl instead presses the “Add new order” option, which brings up another window stating to fill in the order details. He fills in the name slot, selects a pottery type (a stock job, or a custom one), fills in the color slot, records the requested quantity, fills the credit card information, and fills the request date. At the bottom of the window, if nothing was custom-requested within Sondahl’s options, a preset price will be given that can be edited, or a custom price entry is available. There is also an optional expected completion date slot as well. Upon hitting the “submit order” option, the entry window closes and the home window updates, adding into the list of details and sorted to be viewed again if needed. He can choose to click on a snapshot order, which brings up a new window showing the full details of the order including the aforementioned details, in which he can edit with an added feature to update the progress of completion.When satisfied, Sondahl hits the “exit” option which closes the program.  


User Story 1: (Progress Bar) Sondahl has just finished putting an order into the kiln. He still needs to glaze it before he is finished. He opens the program, selects the order from the table, and adjusts the progress slider to 90% or he changes the order stage to “In the kiln.” He then presses ‘save’ and closes the program.


User Story 2: (General Usage) Mr. Sondahl receives an email stating that a customer is ordering 12 thrown cups with blue bands on the outside. He launches up his java program to log the order and inputs the customer’s name, request, date, and approximate price it will cost. He’ll make a phone call to get the customer’s credit card number later, and after he does he can relaunch the program and edit the current entry. He closes the program to go get started on the order.

Team Cormorant Clarifying Questions: 

1. If we have a stage field for where a product is in its creation progress, we’d need to know what the steps/stages to completing a pot order would be.

Client response: 1. Order is received. 2. Pot is thrown 3. Pot is trimmed/assembled 4. Pot is fired for the first time. 5 Pot is glazed/ready for final fire. 6. Pot is ready to ship/be picked up. 

2. Does he want a flashy and good looking UI or does he want to keep it simple and functionally efficient like his website?

Client response: Simple is good. But looking beyond a single customer, as a software marketer bells and whistles bring customers... 

3. Do we get to see what kind of info he stores in his current excel doc so we can have a good understanding of what features he wants?

Client response: I could provide that, but it's pretty haphazard since I didn't bother to define columns. But it tends to be name, address, what is ordered and the price, cost of shipping, status of payment, status of order completion.
