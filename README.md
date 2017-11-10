# Assigner
The assigner sofware is used for randomly assigning addresses for (christamas) cards exchanges. The current version assignes four other participants to each participants, using a graph and network flow theory.

## Usage
The assigner software expects a csv-file created by a google form, meaning that the first column is a timestamp. The following columns/questions are required as the first questions

 - Name:
 - E-mail address:
 - Address:
 - Country:
 - Can you send post outside of your country? (Yes or No)
 - Do you want get a return address to return the favor? (Yes or No)

Each question after those questions is seen as optional. The only class with information about the optional questions is the InputOracle.

After loading the project and creating a executable jar-file, the software can be called using the following command:

	java -jar  Assigner.jar target_csv [target_directory]

If *target_directory* is not specified, the results will be stored in the directory "result". *target_directory* is assumed to be empty or non-existant. A txt-file is created for each participant containting: the name of the participant, the e-mail-address of the participant, all contact information of the receiver of their cards (except e-mail-address), and their answers to the optional questions. The name of the participant is used as the file name.
	
## Theory
The assigner software uses a graph and network flow theory to achieve a random assignment. The flow from the start-vertex to the end-vertex represents an assignment from a sender to a receiver.

### Graph setup
Two vertices are created for each participant: a sender-vertex and a receiver-vertex. These vertices are called the left and right vertex of a participant respectively. The start-vertex is connected to each sender-vertex with an edge with capacity four (the amount of cards one can send) and each receiver-vertex is connected to the end-vertex with an edge with capacity four (the amount of cards one can receive). The connection between the sender-vertex and the receiver-vertex meets the following conditions:

 - A connection from a sender to a receiver is an edge with capacity one.
 - A left vertex of a participant can't be connected to the right vertex of that participant.
 - A sender is able to send to the reciever (for this version: if the sender is not able to send cards abroad, then it can only be connected to receivers in the same country).
 - A receiver is able to return the favor to the sender if he/she wants to (for this version: if the receiver is not able to send cards abroad and wants to return the favor, then it can only receive from senders in the same country).


### Initial flow
The initial flow of the maximum value (Integer.MAX_VALUE) starts from the start-vertex. This flow results in a flow of four to each sender-vertex. In the sender-vertex, edges to receiver-vertices are randomly chosen to continue the flow until there is no flow left (thus four viable receivers-vertices have been found) or there are no unchecked edges. A receiver-vertex can have at most four incoming flows from edges, because the flow is limited by the outgoing edge with capacity four to the end-node. Each flow from a sender-vertex to a receiver-vertex represents an assignment, thus each sender gets four receivers and each receiver gets four senders.

### Fix flow
Because the flow is random, there is a situation that prevent senders from having the desired amount of receivers (and vice-versa). When the only recievers left are none-viable options, either because the receiver-vertices already have four incoming flows or because the sender is not connected to the remainding receiver-vertices, then the sender cannot reach the desired amount of receivers. 

If the desired flow is not reached, then a fix flow is deployed starting from the end-vertex. The fix flow searches a random path composed of open edges (edges with a flow smaller than the capacity) to the start node. If the path ends at a vertex with no open edge towards the start-vertex, then a random satisfied edge (an edge with flow equal to its capacity) is chosen. The fix flow continues from receiver-vertex at the end of the satisfied path. 

If the fix flow reaches	the start-vertex, then a fix flow path has been found.  The size of the flow is the minimum of the flows in the satisfied edges and the remaining capacity of the open edges. The flow of the open edges is increased by that amount, and the flow of the satisfied edges is decreased by that amount.

#### Cyclic flows?
Using the Fix Flow algorithm to reach optimal flow, it seem possible to create cyclic paths where a vertex appears twice or more in a path. To prevent this, the sender-vertices are flaged as part of the current fix flow. If the Fix Flow algorithm encounters  a flaged vertex, then the path will roll back and pick another open edge.

## Adaptation