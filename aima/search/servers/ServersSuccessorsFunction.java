public class ServersSuccessors implements SuccessorFunction {

	public List getSuccessors(Object state) {

		List successors = new ArrayList();
		ServersState servers_state = (ServersState) state;
		for(int i = 0; i<servers_state.getNumPetitions.size();++i){
			int file_id = servers_state.getFileID(i);
			Set servers = servers_state.getServersIDs(file_id);
			Iterator j = servers.iterator();
			while(j.hasNext()){
				ServersState child = makeChild(servers_state,i,j.next());
				if(!servers_state.equals(child)) successors.push_back(child);
			}
		}
		return successors;
	}

	private ServersState makeChild(ServersState parent, int reqID, int servID) {
		ServersState newchild = parent.getCopy();
		newchild.movePetition(reqID,servID);
		return newchild;
	}

}