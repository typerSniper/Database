package com.DBProject.repository;

import com.DBProject.Controller.ajax.CompanyController;
import com.DBProject.Controller.ajax.SelectionController;
import com.DBProject.domain.Company;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.DBProject.domain.Jaf;
import com.DBProject.domain.Student;
import com.DBProject.service.JAFStageManager;

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
	
	@Autowired
    private JAFStageManager jafStageManager;

	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean registerCompany(CompanyController.CompanyRegisterRequest companyRegisterRequest, String stage) {
        String sql = "insert into company values (?, ?, ?, ?, ?, ?);";
        String pass = "insert into password values (?, ?, 'COMPANY');";
		String allo = "insert into ic_company \n" +
				"select ic.ic_id, ? from ic LEFT OUTER JOIN ic_company on ic.ic_id = ic_company.ic_id group by ic.ic_id order by count(ic_company.cid) limit 1;";        try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, companyRegisterRequest.getCid());
			preparedStatement.setString(2, companyRegisterRequest.getName());
			preparedStatement.setString(3, companyRegisterRequest.getContact());
			preparedStatement.setString(4, companyRegisterRequest.getEmail());
			preparedStatement.setString(5, companyRegisterRequest.getRepresentative());
			preparedStatement.setString(6, stage);

			PreparedStatement passwo = connection.prepareStatement(pass);
			passwo.setString(1, companyRegisterRequest.getCid());
			passwo.setString(2, companyRegisterRequest.getPassword());

			PreparedStatement allocate = connection.prepareStatement(allo);
			allocate.setString(1, companyRegisterRequest.getCid());
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
        ps.setString(1, deptName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
			return rs.getString(1);
		}
        return null;
    }


    @SneakyThrows
    String getProgIdFromName (String progName, Connection connection) {
        String sql = "select pid from program where name = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, progName);
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
			SimpleDateFormat df =  new SimpleDateFormat("yyyy-MM-dd");
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, companyId);
			preparedStatement.setString(2, jobRegisterRequest.getJname());
			preparedStatement.setString(3, jobRegisterRequest.getSalary());
			preparedStatement.setString(4, jobRegisterRequest.getLocation());
			preparedStatement.setString(5, jobRegisterRequest.getDescription());
			preparedStatement.setDate(6, new Date(df.parse(jobRegisterRequest.getComp_deadline()).getTime()));
			preparedStatement.setDate(7, null);
			preparedStatement.setString(8, stage);
			int change = preparedStatement.executeUpdate();
			if(change > 0) {
				preparedStatement.close();
				System.out.println(jobRegisterRequest);
				for(CompanyController.Eligiblity t : jobRegisterRequest.getEligibilities()) {
					PreparedStatement ps  =connection.prepareStatement("insert into eligibility select last_value, ?, ?, ?, ? from job_id_sequence;");
					ps.setString(1, companyId);
					ps.setString(2, t.getCpicutoff());
					if(t.getDeptid().toLowerCase().equals("all")) {
						ps.setString(3, "all");
					}
					else {
						String deptId = getDeptIdFromName(t.getDeptid(), connection);
						System.out.println("d" + deptId);
						if(deptId==null)
							return false;
						ps.setString(3, deptId);
					}
					if(t.getProgramid().toLowerCase().equals("all")) {
						ps.setString(4, "all");
					}
					else {
						String progId = getProgIdFromName(t.getProgramid(), connection);
						System.out.println(progId);
						if(progId==null)
							return false;
						ps.setString(4, progId);
					}
					int k = ps.executeUpdate();
					System.out.println(k);
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
		System.out.println(jafID);
    	String sql = "delete from jobs where jid = ?;";
    	try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, jafID);
			int updated = preparedStatement.executeUpdate();
			System.out.println(updated);
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
//    	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			java.util.Date jafDate = sdf2.parse(jobDeadline);
			preparedStatement.setDate(1, new Date(jafDate.getTime()));
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

    @Override //Kshitij
	public List<Jaf> getCompanyJafs(String companyID) {
		String sql = "select jid, cid, jname, salary, location, description, stage, company_deadline, jaf_deadline\n" + 
				"from jobs\n" + 
				"where cid=?";
		List<Jaf> ret = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,companyID);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				String compDeadline = rs.getString(8);
				String jaf_deadline = rs.getString(9);
				java.util.Date compDate = sdf.parse(compDeadline);
				java.util.Date jafDate = sdf.parse(jaf_deadline);
				ret.add(new Jaf(rs.getString(1), rs.getString(3), companyID, rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), compDate, jafDate));
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
		String sql = "select * from jobs;";
		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(sql);
			List<Jaf> jafs = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				jafs.add(getJaf(rs));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

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

	@Override  //Kshitij
	public Jaf getJaf(String jaf) {
		String sql = "select jid, cid, jname, salary, location, description, stage, company_deadline, jaf_deadline\n" +
				"from jobs\n" +
				"where jid=?";
		Jaf ret = null;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,jaf);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				String compDeadline = rs.getString(8);
				String jaf_deadline = rs.getString(9);
				java.util.Date compDate = sdf1.parse(compDeadline);
				java.util.Date jafDate = null;
				if(jaf_deadline!=null)
					jafDate = sdf2.parse(jaf_deadline);
				ret = new Jaf(rs.getString(1), rs.getString(3), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), compDate, jafDate);
			}
			preparedStatement.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override  //Kshitij
	public boolean getIfSigned(String studentID, String jid) {
		String sql = "select count(*)\n" + 
				"from student_jaf\n" + 
				"where jid=? and\n" +
						"sid=?";
		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,jid);
			preparedStatement.setString(2,studentID);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				if(rs.getInt(1) >0) {
					return true;
				} else {
					return false;
				}
			}
			preparedStatement.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override //Kshitij
	public boolean signJaf(String studentID, String jid) {
		String sql = "Insert into student_jaf values(?,?)";
		try(Connection connection = dataSource.getConnection()) {
		    PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,studentID);
			preparedStatement.setString(2,jid);
			int change = preparedStatement.executeUpdate();
			if(change > 0) {
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

	@Override //Kshitij
	public boolean unSignJaf(String studentID, String jid) {
		String sql = "delete from student_jaf where sid=? and jid=?";
		try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,studentID);
			preparedStatement.setString(2,jid);
			int change = preparedStatement.executeUpdate();
			if(change > 0) {
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
	Jaf getJaf(ResultSet rs) {
	    return new Jaf(rs.getString(1), rs.getString(3), rs.getString(2), rs.getString(4)
        , rs.getString(5), rs.getString(6), rs.getString(9), rs.getDate(7), rs.getDate(8));
    }

	@Override
	public List<Jaf> getJafsWithStage(String coordinatorName, String stage) {
	    try(Connection connection = dataSource.getConnection()) {
	        String sql = "select cid from ic_company where ic_id =?";
	        PreparedStatement ps = connection.prepareStatement(sql);
	        ps.setString(1, coordinatorName);
	        ResultSet rs = ps.executeQuery();
	        List<Jaf> jafs= new ArrayList<>();
	        while(rs.next()) {
	        	String cid = rs.getString(1);
                sql = "select * from jobs where cid =? and stage = ?";
                PreparedStatement ps2 = connection.prepareStatement(sql);
                ps2.setString(1, cid);
                ps2.setString(2, stage);
                ResultSet rs2 = ps2.executeQuery();
                while(rs2.next()) {
                    jafs.add(getJaf(rs2));
                }
            }
            return jafs;
        }
        catch (Exception e) {
	        e.printStackTrace();
        }

		return null;
	}

	@Override
	public boolean setJobStages(SelectionController.JobRegisterRequest jobRegisterRequest) {
		String sql = "insert into jaf_handle values(?, ?, ?);";
		try(Connection connection = dataSource.getConnection()) {
			for(SelectionController.JobStages t: jobRegisterRequest.getSelectionProcedure()) {
				PreparedStatement ps = connection.prepareStatement(sql);

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public List<Student> getAllStudents(String jafID) {
		List<Student> ret = new ArrayList<>();
		String sql = "select * from student where sid in (select sid from student_jaf where jid=?)";
		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,jafID);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				ret.add(new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
			}
			preparedStatement.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public boolean selectedStudents(String jafID, List<Student> selections) {
		String sql = "delete from students where sid=?";
		String updatejaf = "update jobs set stage=? where jid=?";
		try(Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			for(Student i : selections) {
				preparedStatement.setString(1, i.getUsername());
				preparedStatement.executeUpdate();
			}
			preparedStatement.close();

			PreparedStatement p2 = connection.prepareStatement(updatejaf);
			p2.setString(1, jafStageManager.getCurrentRep(7));
			p2.setString(2, jafID);
			p2.executeUpdate();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}



}
