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

### Phase 4: Task 2

1. Fri Nov 28 10:22:30 PST 2025
<br> Donation item "item1" added to log with 10 available
2. Fri Nov 28 10:22:34 PST 2025
<br> Donation item "item2" added to log with 20 available
3. Fri Nov 28 10:22:38 PST 2025
<br> Donation item "item3" added to log with 30 available
4. Fri Nov 28 10:22:48 PST 2025
<br> Donation item "item2" status changed to "pending pick up"
5. Fri Nov 28 10:22:55 PST 2025
<br> Donation item "item3" status changed to "picked up"
6. Fri Nov 28 10:23:03 PST 2025
<br> Donation item "item3" added to log with 30 picked up (*should not be printed*)
7. Fri Nov 28 10:23:03 PST 2025
<br> Donation log filtered by status "picked up"
8. Fri Nov 28 10:23:07 PST 2025
<br> Donation item "item2" added to log with 20 pending pick up (*should not be printed*)

There is a bug that I can't figure out how to fix: when a donation item's status is changed (as in 4 and 5), additional events are later logged stating that that item has been added to the log (as in 6 and 8), when really it has just been updated in the log (no new items are actually added).  This "donation added" event is created when addDonation(Donation donation) is called within DonationLog, since I only want the event to be created when a new item is added.  The setter methods in Donation that change a donation's status which creates a "status changed" event are not related to the "donation added" event, so I'm not sure why these extra events are being logged.  The donation log maintains the same number Donation items when items' statuses are changed, so I don't want these events to be created in this case.  When donation items are added to the log, their status is initially set to "available", so these events are not representative of what is happening.

### Phase 4: Task 3

If I had more time to work on this project, I would try to reduce some associations in DonationUI since it has the most fields.  It has a field of ViewLogPanel which I think could be removed, since the UI works without any of the other panels as a field.  This one is more complex because the UI needs to update the ViewLogPanel when changes are made to the donation log.  I could also make FilterLogPanel extend ViewLogPanel, since filtering the log also results in the user viewing a donation log (and they both currently have an association with DonationLogTableModel).  Or I could change ViewLogPanel to an abstract class, and have both a new class ViewEntireLogPanel (to view the entire donation log) and FilterLogPanel (to view a filtered donation log) extend ViewLogPanel.  These design changes could help reduce coupling and code repetition in these classes, keeping the same functionality but making the code and UML easier to understand.