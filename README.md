# JavaFX-x-Data-Structures-Supermarket-Simulation
This repository contains a project that integrates JavaFX and data structures to simulate operations at a supermarket. The Data structures used for this project are  binary search trees and bags.

# How does it work?
The SuperMarketCart acts as a bag to store items purchased by the user. Upon purchase, we search for the item by name in the warehouse, which is represented as a binary search tree. If the item is located in the tree, it is removed to confirm the purchase; we also see a notice on how much the item costs upon purchase. There is also an option for the user to remove an item from the cart, and then it adds that specific item back into the tree. The checkout function resets the entire tree with all items in and shows a notice about the user's total cost from purchase.
