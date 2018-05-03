package com.vedatech.admin.dao;

import com.vedatech.admin.model.Transactions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;


public interface TransactionDao extends CrudRepository<Transactions, Long> {

    @Query("SELECT d FROM Transactions  d  WHERE bankAccount.id=:id ORDER BY date ASC")
    List<Transactions> getAlltransactionsOrderByASC(@Param("id") Long id);

    @Query("SELECT d  FROM Transactions d  WHERE d.date BETWEEN :start AND :end AND bankAccount.id=:id ORDER BY date ASC, id ASC")
    List<Transactions> findTransactionsbyDate(@Param("id") long id, @Param("start") Date start, @Param("end") Date end);

    Transactions findTransactionsById(long id);

    void deleteTransactionsById(Long id);

    @Query("SELECT e.department, e.name, sum (d.checkAmount), sum(d.depositAmount) from Accounts e JOIN e.transactions d WHERE d.date BETWEEN :start AND :end group by e.name order by e.department desc")
    List<Object[]> getSumCheckAmountByDepartmentByType(@Param("start") Date start, @Param("end") Date end);

    @Query("SELECT e.department, e.name, sum (d.depositAmount) from Accounts e JOIN e.transactions d WHERE e.department ='Sales' AND d.date BETWEEN :start AND :end group by e.name order by e.name")
    List<Object[]> getSumDepositAmountByDepartmentByType(@Param("start") Date start, @Param("end") Date end);


	/* @Query("SELECT e.Balance FROM Transactions e  WHERE e.date <= :start ORDER BY date ASC")
	 List<Object[]> findBalanceBf(@Param("start") Date start);
	 
	// @Query("SELECT SUM(e.depositAmount)-SUM(e.checkAmount) FROM Transactions e JOIN e.bankAccount p WHERE e.date < :start AND p.id : id")
	 //double findBalance(@Param("start") Date start, @Param("id") long id);
	 
	 @Query("SELECT d  FROM Transactions d  WHERE bankAccount.id=:id AND d.date <= :start ORDER BY date DESC")
	 List<Transactions> findTransactions(@Param("id") long id, @Param("start") Date start);
	 
	 @Query("SELECT SUM(d.depositAmount)-SUM(d.checkAmount)  FROM Transactions d  WHERE bankAccount.id=:id AND d.date <= :start")
	 Double getBalance(@Param("id") long id, @Param("start") Date start);
	 
	 @Query("SELECT SUM(d.depositAmount)-SUM(d.checkAmount)  FROM Transactions d  WHERE bankAccount.id=:id AND d.date > :start")
	 Double getBalanceOver(@Param("id") long id, @Param("start") Date start);
	 
	 
	 @Query("SELECT d FROM Transactions d  WHERE bankAccount.id=:id AND d.date > :start ORDER BY date ASC")
	 List<Transactions> getTransactionsOver(@Param("id") long id, @Param("start") Date start);
	 
	 @Query("SELECT d FROM Transactions d  WHERE bankAccount.id=:id AND d.date >= :start AND d.id > :idT ORDER BY date ASC")
	 List<Transactions> getTransactionsOverUpdate(@Param("idT") long idT, @Param("id") long id, @Param("start") Date start);
	 
	 @Query("SELECT d  FROM Transactions d  WHERE d.date BETWEEN :start AND :end AND bankAccount.id=:id ORDER BY date ASC, id ASC")
	 List<Transactions> findTransactionsbyDate(@Param("id") long id, @Param("start") Date start, @Param("end") Date end);
	 
	 @Query("SELECT d  FROM Transactions d  WHERE d.date >=:start AND bankAccount.id=:id ORDER BY date ASC, id ASC")
	 List<Transactions> findAllTransactionsbyStartDate(@Param("id") long id, @Param("start") Date start);
	 
	 Transactions findTransactionsById(long id);

		@Query("SELECT d.department, d.type ,d.checkAmount FROM Transactions d WHERE bankAccount.id=:id")
		List<Object> findAllCountsTransactionsById(@Param("id") long id);

		//@Query("SELECT e FROM Professor e WHERE e.department = :dept AND  e.salary = (SELECT MAX(e2.salary) FROM Professor e2  WHERE e2.department = :dept)")

    @Query(
			"SELECT d.name, SUM(e.checkAmount) FROM Department d JOIN d.transactions e " +

					"GROUP BY d.name " +
					"HAVING AVG(e.checkAmount) > 1")
	List<Object> getSumSallaryByGroup();

	//@Query("SELECT d.name,e.type,SUM(e.checkAmount) FROM Department d JOIN d.transactions e group by e.type order by d.name")
	@Query("SELECT e.name, d.type, sum (d.checkAmount) from Department e JOIN e.transactions d WHERE d.department=:id group by d.type order by e.name ")
	//@Query("SELECT e.name, d.type, sum (d.checkAmount) from Transactions d join e.departments group by d.type order by d.department")
	List<Object[]> getSumCheckAmountByDepartmentByType(@Param("id") int id);
	*/
}
