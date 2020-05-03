package com.example.banktest;

import android.content.Context;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AccountUtility {

    private static Map <String, Account> accountMap = new HashMap<>();
    /**
     * Loads accounts from files
     */
    public static void loadAccounts(Context context){
        File folder = new File(context.getFilesDir() + "/accounts");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                JSONObject obj = JsonFileUtility.loadFile(file);
                Account account = new Account(obj);
                accountMap.put(account.getAccountID(), account);
            }
        }
    }

    public static String[] getAccountIDs(){
        return accountMap.keySet().toArray(new String[accountMap.keySet().size()]);
    }

    public static Account[] getAccounts(){
        return accountMap.values().toArray(new Account[accountMap.values().size()]);
    }

    public static Account getAccount(String accountID) {
        return accountMap.get(accountID);
    }

    /**
     * Adds an account to files
     * @param account
     * @param context
     */
    public static void addAccount(Account account, Context context){
        accountMap.put(account.getAccountID(), account);
        JSONObject json = account.makeJSONObject();
        JsonFileUtility.saveFile(json, "accounts", account.getAccountID(), context);
    }

    /**
     * Edit an existing account
     * @param account
     * @param context
     */
    public static void editAccount(Account account, Context context) {
        addAccount(account, context); // Overwrites file and saves to this class internally
    }
}
