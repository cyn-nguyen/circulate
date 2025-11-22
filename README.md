# circulate

### An application that supports redistribution of surplus food from grocery stores to food insecure communities

A large fraction of all food produced in Canada ends up wasted throughout all phases of food production, processing, transportation and storage.  Much of this food waste is avoidable, meaning food is discarded when it could've otherwise been eaten.  In line with the rising costs of living, food insecurity has increased in recent years.  This is an issue that can be addressed by redirecting surplus food that would otherwise go to waste.  **circulate** aims to improve food insecurity by reducing food waste at the retail level, redirecting cosmetically imperfect food, food nearing best before dates or expiry dates, and surplus food due to poor inventory management from grocery stores to charitable partners for redistribution.  This is one small step that can move us closer to establishing a circular food economy with zero waste.

This application is a donation logging tool that grocery stores can use to track items that are up for donation to food banks, local food charities, or community pantries.  Users can enter what items are available, mark items as pending or picked up, and view the entire donation log.  For this course, I will confine all user inputs to be from the grocery store end.  At the grocery store level, this application will provide data on what items are commonly put up for donation, what items are in high demand for charitable partners and what items remain to be redistributed before spoilage, which will assist in optimizing food redistribution processes and hopefully improve inventory planning long-term.  There is an opportunity to expand the application further to allow inputs from donation recipients, such as viewing what items are available and claiming what they would like to pick up.  This is something I will work on outside of this course as I am very passionate about food issues, having worked in the nutrition and food space, as well as being involved in local food initiatives.

### User stories
As a user, I want to be able to:
- add an available donation item into the donation log, specifying the name of the item and the quantity available
- update a donation item's status to available, pending pick-up, or picked up
- view the entire donation log and keep count of all items (of any status) in the log
- filter the donation log to view and keep count of the items currently available, pending pick-up, or picked up
- have the option to save all updates made to the donation log to file
- have the option to load a donation log from file

### Instructions for end user
- You can view the panel that displays the Xs that have already been added to the Y by <b>Actions > View donation log</b>
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by <b>Actions > Add a donation item</b>
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by <b>Actions > Filter donation log by item status</b>
    - You can change an item's status by <b>Actions > Change donation item status</b> so there are items with different statuses
- You can locate my visual component by running the application (a splash screen will be displayed while the application is loading)
- You can save the state of my application by <b>File > Save donation log to file</b>
- You can reload the state of my application by <b>File > Load previous donation log from file</b>