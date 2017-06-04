package plan;

import java.util.LinkedList;

import expr.Expr;
import prototype.Schema;
import scan.ProjectScan;
import scan.Scan;

public class ProjectPlan implements Plan {
	Schema schema;
	Plan fatherPlan;
	Plan subPlan;
	LinkedList<Expr> exprList;
	
	public ProjectPlan(LinkedList<Expr> exprs, Plan p) {
		exprList = exprs;
		subPlan = p;
		subPlan.setFather(this);
		// TODO: project plan schema
		schema = subPlan.getSchema();
	}
	
	@Override
	public Scan start() {
		return new ProjectScan(subPlan.start(), schema);
	}

	@Override
	public Schema getSchema() {
		return schema;
	}

	@Override
	public LinkedList<Plan> getChildren() {
		LinkedList<Plan> ret = new LinkedList<Plan>();
		ret.add(subPlan);
		return ret;
	}

	@Override
	public Plan setChildren(LinkedList<Plan> plans) {
		if (plans.size() >= 1) {
			subPlan = plans.get(0);
		}
		return this;
	}

	@Override
	public Plan getFather() {
		return fatherPlan;
	}

	@Override
	public Plan setFather(Plan fa) {
		fatherPlan = fa;
		return this;
	}

	@Override
	public String toString() {
		return "Project ";
	}
}