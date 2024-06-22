package ServerSide.DAO;

import Models.Bank_Models;

import java.util.ArrayList;

public class BankDAO implements DAOInterface<Bank_Models>{
    @Override
    public int insert(Bank_Models bankModels) {
        return 0;
    }

    @Override
    public int update(Bank_Models bankModels) {
        return 0;
    }

    @Override
    public int delete(Bank_Models bankModels) {
        return 0;
    }

    @Override
    public ArrayList<Bank_Models> selectAll() {
        return null;
    }

    @Override
    public Bank_Models selectById(Bank_Models bankModels) {
        return null;
    }

    @Override
    public ArrayList<Bank_Models> selectByCondition(String condition) {
        return null;
    }
}
