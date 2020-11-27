package bdd.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.SimpleTransactionStatus;


public class SpringTransactionHooks implements BeanFactoryAware {

    private BeanFactory beanFactory;
    private String txnManagerBeanName;
    private TransactionStatus transactionStatus;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    /**
     * @return the (optional) bean name for the transaction manager to be
     * obtained - if null, attempt will be made to find a transaction manager
     * by bean type
     */
    public String getTxnManagerBeanName() {
        return txnManagerBeanName;
    }

    /**
     * Setter to allow (optional) bean name to be specified for transaction
     * manager bean - if null, attempt will be made to find a transaction manager
     * by bean type
     *
     * @param txnManagerBeanName bean name of transaction manager bean
     */
    public void setTxnManagerBeanName(String txnManagerBeanName) {
        this.txnManagerBeanName = txnManagerBeanName;
    }

    @Before(value = "@txn", order = 100)
    public void startTransaction() {
        transactionStatus = obtainPlatformTransactionManager()
                .getTransaction(new DefaultTransactionDefinition());
    }

    @After(value = "@txn", order = 100)
    public void rollBackTransaction() {
        obtainPlatformTransactionManager()
                .rollback(transactionStatus);
    }

    public PlatformTransactionManager obtainPlatformTransactionManager() {
        if (txnManagerBeanName == null) {
            return beanFactory.getBean(PlatformTransactionManager.class);
        } else {
            return beanFactory.getBean(txnManagerBeanName, PlatformTransactionManager.class);
        }
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(SimpleTransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}