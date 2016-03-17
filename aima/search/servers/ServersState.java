package aima.search.servers;

import Servers;
import Requests;

public class ServersState {
    
    private int[] petitionLoc;
    private static boolean instantiated = false;
    private static final Requests requests;
    private static final Servers servers;
    
    public ServerState(
        int users,
        int nrequests,
        int rseed,
        int nserv,
        int nrep,
        int sseed) {
        
        if (not instantiated) {
        	instantiated = true;
	        requests = new Requests(users,nrequests,rseed);
	        servers = new Servers(nserv,nrep,sseed);
        }
        petitionLoc = new int[requests.size()];
    }
    
    public ServerState getCopy() {
		return ServerState(0,0,0,0,0,0);
	}
    
    public int getNumPetitions() {
    	return requests.size();
    }
    
    public int getNumServers() {
    	return servers.size();
    }
    
    public int getServerID(int i) {
        return petitionLoc[i];
    }

	public void movePetition(int petitionID, int serverID) {
		petitionLoc[petitionID] = serverID;
	}
	
	public int getUserID(int petitionID) {
		return requests.getRequest(petitionID)[0];
	}
	
	public int getFileID(int petitionID) {
		return requests.getRequest(petitionID)[1];
	}
	
	public int[] getTransmissionTime(int petitionID) {
		int userID = getUserID(petitionID);
		int serverID = petitionLoc[petitionID];
		int ret[2] = [servers.tranmissionTime(userID,serverID),serverID]
		return ret;
	}
	
	public int fileInServer(int fileID, int serverID) {
	    return servers.fileLocations(fileID).contains(serverID);
	}


    //Mejorar despues
	public int hashCode() {
		return 0;
	}

	public boolean equals(Object o) {
		ServersState aState = (ServersState) o;
		boolean retval = true;
		for (int i = 0; retval and i < petitionLoc.length; i++)
		    retval = retVal and this.getServerID(i) == aState.getPositionOf(i);
		return retval;
	}
    
	public String toString() {
		String retVal="";
		for (int i=0; i<petitionLoc.length; i++) {
		    retVal = retVal + "Petition " + i + ": " + this.getServerID(i);
		}
		return retVal;
	}

}