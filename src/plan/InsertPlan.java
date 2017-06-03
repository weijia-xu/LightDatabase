package plan;

import java.util.LinkedList;

import prototype.Schema;
import prototype.Table;
import scan.Scan;

public class InsertPlan implements Plan {
	// Table to be inserted.
	private Table table;
	// Plan to insert.
	private Plan subPlan;

	public InsertPlan(Table tbl, Plan p) {
		table = tbl;
		subPlan = p;
		subPlan.setFather(this);
	}

	@Override
	public Scan start() {
		return subPlan.start();
	}

	@Override
	public Schema schema() {
		return table.getSchema();
	}

	@Override
	public LinkedList<Plan> getChildren() {
		LinkedList<Plan> ret = new LinkedList<Plan>();
		ret.add(subPlan);
		return ret;
	}

	@Override
	public Plan setChildren(LinkedList<Plan> plans) {
		subPlan = plans.get(0);
		return this;
	}

	@Override
	public Plan getFather() {
		return null;
	}

	@Override
	public Plan setFather(Plan fa) {
		return this;
	}

	@Override
	public String toString() {
		return "Insert ";
	}
}