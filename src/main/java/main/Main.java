package main;

import dao.OracleDao;
import dao.OracleDaoHibernate;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OracleDao odao = new OracleDaoHibernate();
		odao.getKlant(1);
	}

}
