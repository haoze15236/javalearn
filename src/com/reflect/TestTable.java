package com.reflect;

import java.util.Map;


@belong("zee")
@Table("test_table")
public class TestTable {
	
	@Column(name="table_id",type="number")
    private int id;

	@Column(name="table_name",type="varchar2",length=4000)
	private String tableName;
	
	public String tabletext;
	
	private Map<Integer,String> map;
	
	private String[][] arr;

	public TestTable(int id, String tableName) {
		super();
		this.id = id;
		this.tableName = tableName;
	}

	public TestTable() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public void setMap(Map<Integer,String> map){
		this.map = map;
	}
	
	public Map<Integer,String> getMap(){
		return this.map;
	}

	public String[][] getArr() {
		return arr;
	}

	public void setArr(String[][] arr) {
		this.arr = arr;
	}
	
}
