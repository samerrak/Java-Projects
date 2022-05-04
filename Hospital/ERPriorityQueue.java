import java.util.ArrayList;
import java.util.HashMap;

public class ERPriorityQueue{

	public ArrayList<Patient>  patients;
	public HashMap<String,Integer>  nameToIndex;

	public ERPriorityQueue(){

		//  use a dummy node so that indexing starts at 1, not 0

		patients = new ArrayList<>();
		patients.add( new Patient("dummy", 0.0) );

		nameToIndex  = new HashMap<>();
	}


	private int parent(int i){
		return i/2;
	}

	private int leftChild(int i){
	    return 2*i;
	}

	private int rightChild(int i){
	    return 2*i+1;
	}

    /*

    TODO: OPTIONAL
    TODO: Additional helper methods such as isLeaf(int i), isEmpty(), swap(int i, int j) could be useful for this assignment
     */

	public void swap(int i, int j){
		Patient tmp = this.patients.get(i);
		Patient tmp2 = this.patients.get(j);
		this.patients.set(j, tmp);
		this.patients.set(i,tmp2);
		this.nameToIndex.put(tmp.getName(), j);
		this.nameToIndex.put(tmp2.getName(), i);
	}

	public boolean isLeaf(int i) // true if is leaf
	{
		return (i >= this.patients.size()/2) && (i < this.patients.size());
	}

	public void upHeap(int i){

		while (i > 1 && this.patients.get(i).getPriority() < this.patients.get(parent(i)).getPriority())
		{
			swap(i, parent(i));
			i=i/2; //you can do it recursively
		}
		// Done
	}

	public void downHeap(int i){
		int maxIndex = this.patients.size();
		int child;
		while (leftChild(i) < maxIndex){
			child = leftChild(i);
			if (child < maxIndex){
				if ((maxIndex - rightChild(i) > 0) && this.patients.get(rightChild(i)).getPriority() < this.patients.get(leftChild(i)).getPriority())
					child = child + 1;
			}
			if (this.patients.get(child).getPriority() < this.patients.get(i).getPriority()){
				swap(i, child);
				i = child;
			}
			else
				break;
		}
        // TODO: Implement your code here
	}

	public boolean contains(String name){

		return this.nameToIndex.containsKey(name);

        // TODO: Implement your code here & remove return statement
	}

	public double getPriority(String name){
		if (this.contains(name)) {
			int index = this.nameToIndex.get(name);
			Patient x = this.patients.get(index);
			return x.getPriority();
		}
		else
			return -1;
		//or implement a for loop, and loop through strings in Patient and check if equal, if its equal store the value of that index ang then get priority but that's O(n)
	}

	public double getMinPriority(){
		if (nameToIndex.isEmpty()) {
			return -1;
		}
		else
			return this.patients.get(1).getPriority();
	}


	public String removeMin(){
		if (nameToIndex.isEmpty())
			return null;
		else {
			Patient x = this.patients.get(1);
			String name = this.patients.get(this.patients.size()-1).getName();
			int index = this.nameToIndex.get(x.name);
			this.patients.set(1,this.patients.get(this.patients.size() -1));
			this.nameToIndex.put(name, 1);
			this.nameToIndex.remove(x.getName());
			this.patients.remove(this.patients.size() - 1);
			downHeap(1);
        	return x.getName();}
	}

	public String peekMin() {
		if (nameToIndex.isEmpty())
			return null;
		else
			return this.patients.get(patients.size()-1).getName();

	}


	public boolean add(String name, double priority){
		int x = this.patients.size();
		if (!this.contains(name)) {
			this.patients.add(x, new Patient(name,priority));
			this.nameToIndex.put(name, x);
			upHeap(x);
			return true;
			}
		else
			return false;
	}

	public boolean  add(String name){
		if (!this.contains(name)) {
			int x = this.patients.size();
			this.patients.add(x, new Patient(name, Double.POSITIVE_INFINITY));
			this.nameToIndex.put(name, x);
			return true;
		}
		else
			return false;
	}

	public boolean remove(String name){
		if (!this.contains(name)){
			return false;
		}
		else {
			int i = this.nameToIndex.get(name);
			Patient p = this.patients.get(this.patients.size()-1);
			this.patients.set(i, this.patients.get(this.patients.size()-1));
			this.patients.remove(this.patients.size()-1);
			this.nameToIndex.remove(name, i);
			boolean f = isLeaf(i);

			if (f)
			{
				this.nameToIndex.put(p.getName(), i);
			}

			if (p.getPriority() > this.patients.get(parent(i)).getPriority())
				downHeap(i);
			else
				upHeap(i);
			return true;
		}
	}

	public boolean changePriority(String name, double priority){
		int index;
		if (!this.contains(name))
			return false;
		else {
			// you dont need to change the priority because name is not changed.
			index = this.nameToIndex.get(name);
			Patient xCopy = new Patient(name, priority);
			this.patients.set(index, xCopy);
			if (xCopy.getPriority() > this.patients.get(parent(index)).getPriority())
				downHeap(index);
			else
				upHeap(index);
			return true;
		}
	}

	public ArrayList<Patient> removeUrgentPatients(double threshold){
		int index;
		ArrayList<Patient> removedPatients = new ArrayList<Patient>();
		ArrayList<Patient> copyList = new ArrayList<Patient>(this.patients);
		if (!nameToIndex.isEmpty()) {
			for (Patient x : copyList) {
				if (!x.getName().equals("dummy")) {
					index = this.nameToIndex.get(x.getName());
					if (x.getPriority() <= threshold) {
						removedPatients.add(x);
						remove(x.getName());
					}

				}
			}
			return removedPatients;
		}
		else
			return null;
	}

	public ArrayList<Patient> removeNonUrgentPatients(double threshold){
		int index;
		ArrayList<Patient> removedPatients = new ArrayList<>();
		ArrayList<Patient> copyList = new ArrayList<>(this.patients);
		if (!nameToIndex.isEmpty()) {
			for (Patient x : copyList) {
				if (!x.getName().equals("dummy")) {
					index = this.nameToIndex.get(x.getName());
					if (x.getPriority() >= threshold) {
						removedPatients.add(x);
						remove(x.getName());}
				}}
			return removedPatients;}
		else
			return null;
	}



	static class Patient{
		private String name;
		private double priority;

		Patient(String name,  double priority){
			this.name = name;
			this.priority = priority;
		}

		Patient(Patient otherPatient){
			this.name = otherPatient.name;
			this.priority = otherPatient.priority;
		}

		double getPriority() {
			return this.priority;
		}

		void setPriority(double priority) {
			this.priority = priority;
		}

		String getName() {
			return this.name;
		}

		@Override
		public String toString(){
			return this.name + " - " + this.priority;
		}

		public boolean equals(Object obj){
			if (!(obj instanceof Patient otherPatient)) return false;
			return this.name.equals(otherPatient.name) && this.priority == otherPatient.priority;
		}

	}
}
