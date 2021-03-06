package com.chinahanjiang.crm.pojo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 任务 2016-8-15
 * @author tree
 *
 */
@Entity
@Table(name="Task")
public class Task {

	private int id;
	
	private String name;
	
	private String code;
	
	private User createUser;
	
	private int status;/*0-进行中,1-完成,2-放弃*/
	
	private int isDelete;/*0-删除,1-没删除*/
	
	private Timestamp createTime;
	
	private Timestamp updateTime;
	
	private User updateUser;
	
	private Customer customer;
	
	private List<Item> items;
	
	private List<Product> products;
	
	private int flag;//是否修改了产品配置 1表示修改了配置,0表示没有
	
	private TaskType taskType;
	
	private String remarks;
	
	public Task(){
		
		this.status = 0;
		this.isDelete = 1;
		this.flag = 1;
	}

	public Task(int id, String name, String code, User createUser, int status, int isDelete,
			Timestamp createTime, Timestamp updateTime, User updateUser,int flag,
			Customer customer, List<Item> items, TaskType taskType, List<Product> products,
			String remarks) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.createUser = createUser;
		this.status = status;
		this.isDelete = isDelete;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.updateUser = updateUser;
		this.customer = customer;
		this.items = items;
		this.taskType = taskType;
		this.products = products;
		this.remarks = remarks;
		this.flag = flag;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "t_id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="t_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="t_code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "t_cuid",referencedColumnName="u_id")
	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	@Column(name="t_status")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name="t_isDelete")
	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name="t_createTime")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name="t_updateTime")
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "t_uid",referencedColumnName="u_id")
	public User getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "t_cid",referencedColumnName="c_id")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@OneToMany(targetEntity = Item.class, cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "task")
	//@Fetch(FetchMode.SUBSELECT)
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_ttid",referencedColumnName="tt_id")
	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	@Column(name="t_remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "TaskProducts",
	joinColumns = {@JoinColumn(name = "tp_tid", referencedColumnName = "t_id")},
	inverseJoinColumns = {@JoinColumn(name = "tp_pid", referencedColumnName ="p_id")})
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Column(name="t_flag")
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
