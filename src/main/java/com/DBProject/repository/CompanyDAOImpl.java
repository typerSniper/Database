package com.DBProject.repository;

import com.DBProject.Controller.ajax.CompanyController;
import com.DBProject.domain.Company;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.DBProject.domain.Jaf;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Jatin on 23/11/17.
 */

@Repository
public class CompanyDAOImpl implements CompanyDAO {
	@Autowired
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean registerCompany(CompanyController.CompanyRegisterRequest companyRegisterRequest, String stage) {
		//TODO: fix this shit

        String sql = "insert into company values (nextval('company_id_sequence'), ?, ?, ?, ?, ?);";
        String pass = "insert into password values (select last_value from company_id_sequence, ?);";
        String allo = "insert into ic_company values (select ic.ic_id from ic LEFT OUTER JOIN ic_company on ic.ic_id = ic_company.ic_id group by ic.ic_id order by count(ic_company.cid) limit 1, select last_value from company_id_sequence);";
        try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, companyRegisterRequest.getName());
			preparedStatement.setString(2, companyRegisterRequest.getContact());
			preparedStatement.setString(3, companyRegisterRequest.getEmail());
			preparedStatement.setString(4, companyRegisterRequest.getRepresentative());
			preparedStatement.setString(5, stage);

			PreparedStatement passwo = connection.prepareStatement(pass);
			passwo.setString(1, companyRegisterRequest.getPassword());
			
			PreparedStatement allocate = connection.prepareStatement(allo);
			int change = preparedStatement.executeUpdate();
			int change2 = passwo.executeUpdate();
			int change3 = allocate.executeUpdate();
			if(change > 0 && change2 > 0 && change3 > 0) {
				return true;
			} else {
				return false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        return false;
    }

    @SneakyThrows
    String getDeptIdFromName (String deptName, Connection connection) {
		String sql = "select did from department where name =?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, deptName.toUpperCase());
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			return rs.getString(1);
		}
		return null;
	}


	@SneakyThrows
	String getProgIdFromName (String progName, Connection connection) {
		String sql = "select pid from program where name = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, progName.toUpperCase());
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			return rs.getString(1);
		}
		return null;
	}

    @Override
    public boolean registerJob (String companyId, String stage, CompanyController.JobRegisterRequest jobRegisterRequest) {
    	String sql = "insert into jobs values (nextval('job_id_sequence'), ?, ?, ?, ?, ?, ?, ?, ?);";
    	try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, companyId);
			preparedStatement.setString(2, jobRegisterRequest.getJname());
			preparedStatement.setString(3, jobRegisterRequest.getSalary());
			preparedStatement.setString(4, jobRegisterRequest.getLocation());
			preparedStatement.setString(5, jobRegisterRequest.getDescription());
			preparedStatement.setString(6, stage);
			preparedStatement.setDate(7, Date.valueOf(jobRegisterRequest.getComp_deadline()));
			preparedStatement.setDate(8, null);
			int change = preparedStatement.executeUpdate();
			if(change > 0) {
				preparedStatement.close();
				for(CompanyController.Eligiblity t : jobRegisterRequest.getEligiblities()) {
					PreparedStatement ps  =connection.prepareStatement("insert into eligibility values( jid =  select avg(last_value) from job_id_sequence, cid=?, cpicutoff =?, deptid = ?, programid = ? )");
					ps.setString(1, companyId);
					ps.setString(2, t.getCpicutoff());
					if(t.getDeptid().toLowerCase().equals("all")) {
						ps.setString(3, "all");
					}
					else {
						String deptId = getDeptIdFromName(t.getDeptid(), connection);
						if(deptId==null)
							return false;
						ps.setString(3, deptId);
					}
					if(t.getProgramid().toLowerCase().equals("all")) {
						ps.setString(4, "all");
					}
					else {
						String progId = getProgIdFromName(t.getProgramid(), connection);
						if(progId==null)
							return false;
						ps.setString(4, progId);
					}
					ps.executeUpdate();
					ps.close();
				}
				return true;
			} else {
				return false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        return false;
    }

    @Override
    public Company getCompany(String username) {
    	String sql = "select cid, name, contact, email, stage\n" + 
    			"from company\n" + 
    			"where cid=?";
    	Company ret = null;
    	try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				ret = new Company(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        return ret;
    }

    @Override
    public  boolean setJafStage(String jafID, String stage) {
    	String sql = "update jobs set stage=? where jid=?";
    	try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, stage);
			preparedStatement.setString(2, jafID);
			int updated = preparedStatement.executeUpdate();
			if (updated > 0) {
				return true;
			} else {
				return false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        return false;
    }

    @Override
    public boolean deleteJaf(String jafID) {
    	String sql = "delete from jobs where jid = ?;";
    	try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, jafID);
			int updated = preparedStatement.executeUpdate();
			if (updated > 0) {
				return true;
			} else {
				return false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        return false;
    }

    @Override
    public boolean setJobDeadline(String jafID, String jobDeadline) {
    	String sql = "update jobs set jaf_deadline=? where jid=?";
    	try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, jobDeadline);
			preparedStatement.setString(2, jafID);
			int updated = preparedStatement.executeUpdate();
			if (updated > 0) {
				return true;
			} else {
				return false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        return false;
    }

    @Override
	public List<Jaf> getCompanyJafs(String companyID) {
		String sql = "select jid, cid, jname, salary, location, description, stage, company_deadline, jaf_deadline\n" + 
				"from jobs\n" + 
				"where cid=?";
		List<Jaf> ret = new ArrayList<>();

		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,companyID);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				ret.add(new Jaf(rs.getString(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8), rs.getDate(9)));
			}
			preparedStatement.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public List<Jaf> getAllJafs() {
		//TODO
		return null;
	}

	@Override
	public boolean getEligible(String username, String jid) {
		String sql = "select CPI, pid, did from student where sid = ?";
		try(Connection conn = dataSource.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			String cpi ="", did =" ", pid="";
			while(rs.next()) {
				cpi = rs.getString(1);
				pid = rs.getString(2);
				did = rs.getString(3);
			}
			ps.close();
			PreparedStatement ps2 = conn.prepareStatement("Select cpicutoff, deptid, programid from eligibility where jid = ?");
			ps2.setString(1, jid);
			ps.setString(1, jid);
			boolean eligible = false;
			ResultSet rs2 = ps.executeQuery();
			while(rs2.next()&&!eligible) {
				Integer cpiReq = Integer.parseInt(rs.getString(1)) ;
				String pidReq = rs.getString(3);
				String didReq = rs.getString(2) ;
				eligible = (cpiReq==0 || Integer.parseInt(cpi) >cpiReq) &&
						(pidReq.toLowerCase().equals("any") || pidReq.equals(pid)) &&
						didReq.toLowerCase().equals("any") || didReq.equals(did);
			}
			return eligible;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Jaf getJaf(String jaf) {
		//TODO:
		return null;
	}

	@Override
	public boolean getIfSigned(String studentID, String jid) {
		//TODO:
		return true;
	}

	@Override
	public boolean signJaf(String studentID, String jid) {
		//TODO
		return true;
	}

	@Override
	public boolean unSignJaf(String studentID, String jid) {
		//TODO
		return true;
	}

	@Override
	public List<Jaf> getJafsWithStage(String coordinatorName, String stage) {
		//TODO:
		return null;
	}



}
