/*
 * Created on Mar 15, 2016
 *
 */
//package aima.search.eightpuzzle;

//import aima.basic.XYLocation;
//import aima.search.eightpuzzle.EightPuzzleBoard;
import aima.search.framework.HeuristicFunction;
import java.lang.math;

/**
 * @author Joan Grau
 *  
 */
public class HeuristicFunctionServers implements HeuristicFunction {
	
	//criteri 1 optimització
	public double getHeuristicValue(Object state) {
		ServersState estat = (ServersState) state;
		int retVal = 0;
		for(int i=0; i < estat.getNumPetitions(); ++i){
			retVal += estat.getTransmissionTime()[0];
		}
		return retVal;

	}
	
	//criteri 2 optimització
	public double getHeuristicValue2(Object state) {
		ServersState estat = (ServersState) state;
		double retVal, mitjana = 0,sum = 0;
		int nserv = estat.getNumServers();
		int serv[] = new int[nserv];
		for(int i=0; i < estat.getNumPetitions(); ++i){//posar temps als servers
			int peticio[];
			peticio = estat.getTransmissionTime();
			serv[peticio[1]] += peticio[0];
		}
		for(int i=0; i < nserv; ++i){
			mitjana += (double)serv[i];
		}
		mitjana /= (double)nserv;
		for(int i=0; i < nserv; ++i){
			sum += ((double)serv[i]-mitjana)*((double)serv[i]-mitjana);
		}
		retVal = sqrt(1./(nserv-1)*sum);
		return retVal;

	}

}