import java.util.*;
import java.io.*;
public class Control{
    public Scanner sc = new Scanner(System.in);


    public String command="";
    public int num_account;
    public String[][] account = new String[100][2];
    public int num_catalog;
    public String[] catalog = new String[100];

    Data d= new Data();

    public Control() throws FileNotFoundException{
        accountRenew();
        catalogRenew();
    }
    public void catalogRenew() throws FileNotFoundException{
        Scanner Catalog = new Scanner(new FileInputStream("catalog.txt"));
        int i=0;
        while(Catalog.hasNext()){
            catalog[i]=Catalog.next();
            i++;

        }
        num_catalog = i;
    }
    public void accountRenew()throws FileNotFoundException{
        Scanner Account = new Scanner(new FileInputStream("account.txt"));
        int i=0;
        while(Account.hasNext()){
            account[i][0] = Account.next();
            account[i][1] = Account.next();
            i++;
        }
        num_account =i;
    }
    public void doLogIn()throws FileNotFoundException{
        boolean totalpass=false;
        boolean accountPass = false;
        for(int i =0;i<3;i++){
            System.out.println("Account:");
            String inputAccount = sc.next();
            System.out.println("Password:");
            String inputPwd = sc.next();
            System.out.println("Verify_string:"+getVerify()+"\nInput_Verify_string:");
            String verify = sc.next();
            //
            
            int accountLocate=0;
            for(int y=0;y<num_account;y++){
                if(inputAccount.equals(account[y][0])){
                    accountPass = true;
                    accountLocate=y;
                    
                }
            }
            if(accountPass){
                if(inputPwd.equals(account[accountLocate][1])&&verify.equals(getVerify())){
                    totalpass = true;
                    break;
                }
                else{
                    System.out.println("Error_wrong_account_password_or_verify_string");
                }
            }
            else{
                System.out.println("Error_wrong_account_password_or_verify_string");
            }
            
            
        }
        
        if(totalpass){
            System.out.println("Login_success");
            doMainMenu();
        }
        else{
            
            System.exit(0);
        }
        
        
        


    }
    public String getVerify()throws FileNotFoundException{
        Scanner Verify = new Scanner(new FileInputStream("config.txt"));
        String verify=null;
        while(Verify.hasNext()){
            verify = Verify.next();
            if(verify.equals("verify_string:")){
                verify = Verify.next();
                break;
            }
        }
        Verify.close();
        return verify;
    }

    public void doMainMenu()throws FileNotFoundException{
        System.out.println("****************************************\n"+"1.Show_a 2.Show_p 3.Show_by_c 4.Search 5.Mod 6.Del 7.Add_cont"+"\n"+"8.Add_cat 9.Show_cat 10.Set_field 11.Set_page 12.Set_order 13.Set_sort\n"+
        "14.Show_r 15.Opt 16.Show_acc 17.Add_acc 18.Del_acc 19.Mod_acc 20.Logout 99.Exit\n"+"****************************************");
        doMenuSelect();
    }
    public void doMenuSelect() throws FileNotFoundException{
        command = sc.next();

        switch(command){
            case "1":
                doShowAll();
                break;
            case "2":
                doShowPage();
                break;
            case "3":
                doShowByC();
                break;
            case "4":
                doSearch();
                break;
            case "5":
                doMod();
                break;
            case "6":
                doDel();
                break;
            case "7":
                doAddCont();
                break;
            case "8":
                doAddCatalog();
                break;
            case "9":
                doShowCatalog();
                break;
            case "10":
                doSetField();
                break;
            case "11":
                doSetPage();
                break;
            case "12":
                doSetOrder();
                break;
            case "13":
                doSerSort();
                break;
            case "14":
                doShowRawData();
                break;
            case "15":
                doOpt();
                break;
            case "16":
                doShowAccount();
                break;
            case "17":
                doAddAccount();
                break;
            case "18":
                doDeleAccount();
                break;
            case "19":
                doModAccount();
                break;
            case "20":
                doLogOut();
                break;
            case "99":
                System.exit(0);
            default :
                System.out.println("Error_wrong_command\n"+"Please_enter_again:");
                doMenuSelect();
                break;
        }
    }
    public void doSubMenu()throws FileNotFoundException{
        System.out.println("[0].Go_back_to_main_menu [99].Exit_system");
        
        while(true){
            command = sc.next();
            switch(command){
                case "0":
                    doMainMenu();
                    break;
                
                case "99":
                    System.exit(0);
    
                default:
                System.out.println("Error_wrong_command\n"+"Please_enter_again:");
                
                
                
            }
        
        }
    }


    ///function
    //1
    public void doShowAll() throws FileNotFoundException{
        d.dataRenew();
        System.out.print("[ID] ");
        if(d.show[0]==1)
        System.out.print("[Name]       ");
        if(d.show[1]==1)
        System.out.print("[Phone]     ");
        if(d.show[2]==1)
        System.out.print("[Catalog]    ");
        if(d.show[3]==1)
        System.out.print("[Email]                  ");
        if(d.show[4]==1)
        System.out.print("[BD]");
        System.out.println();
        // System.out.print("[ID] [Name]       [Phone]     [Catalog]    [Email]                  [BD]");
        for(int i=0;i<d.num_data;i++){
            for(int j=0;j<6;j++){
                if(j==0){
                    System.out.printf("%-4s ",d.data[i][0]);
                }
                if(j==1){
                    if(d.show[0]==1){
                        System.out.printf("%-12s ",d.data[i][1]);
                    }
                    
                }
                if(j==2){
                    if(d.show[1]==1)
                    System.out.printf("%-11s ",d.data[i][2]);
                }
                if(j==3){
                    if(d.show[2]==1)
                    System.out.printf("%-12s ",d.data[i][3]);
                }
                if(j==4){
                    if(d.show[3]==1)
                    System.out.printf("%-24s ",d.data[i][4]);
                }
                if(j==5){
                    if(d.show[4]==1)
                    System.out.printf("%-4s",d.data[i][5]);
                }
            }
            System.out.println();
        }
        doSubMenu();
    }
    //2
    public void doShowPage() throws FileNotFoundException{
        int inputPage=0;
        System.out.println("Choose_show_per_page:\n"+"[3].3_data_per_page [5].5_data_per_page [10].10_data_per_page\n"+"[d].default [0].Go_back_to_main_menu [99].Exit_system");
        String se=sc.next();
        switch(se){
            case "3":
                inputPage=3;
                break;
            case "5":
                inputPage=5;
                break;
            case "10":
                inputPage=10;
                break;
            case "d":
                inputPage=d.page;
                break;
            case "0":
                doMainMenu();
                break;
            case "99":
                System.exit(0);
        }

        for(int i = 0; i < d.num_data; i++){
            System.out.printf("%-4s %-12s %-11s %-12s %-24s %-4s\n", d.data[i][0], d.data[i][1], d.data[i][2], d.data[i][3], d.data[i][4], d.data[i][5]);
            if((i % inputPage == inputPage - 1 || i == d.num_data - 1) && inputPage < d.num_data){
                if(i / inputPage == 0){
                    System.out.println("[2].Next_page [0].Go_back_to_main_menu [99].Exit_system");
                    boolean check = true;
                    int A=0;
                    while(check){
                        String a = sc.next();
                        switch(a){
                            case "2":
                                A=0;
                                check=false;
                                break;
                            case "0":
                                doMainMenu();
                                check=false;
                                break;
                            case "99":
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Error_wrong_command\n"+"Please_enter_again:");
                        
                        }
                    }
                    i += inputPage * A + ((i + 1) % inputPage);
                    }
                else if(i == d.num_data - 1){
                    System.out.println("[1].Last_page [0].Go_back_to_main_menu [99].Exit_system");
                    boolean check = true;
                    int A=0;
                    while(check){
                        String a = sc.next();
                        switch(a){
                            case "1":
                                A=-2;
                                check=false;
                                break;
                            case "0":
                                doMainMenu();
                                check=false;
                                break;
                            case "99":
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Error_wrong_command\n"+"Please_enter_again:");
                        
                        }
                    }
                    i += inputPage * A + ((i + 1) % inputPage);
                }
                else{
                    System.out.println("[1].Last_page [2].Next_page [0].Go_back_to_main_menu [99].Exit_system");
                    boolean check = true;
                    int A=0;
                    while(check){
                        String a = sc.next();
                        switch(a){
                            case "1":
                                A=-2;
                                check=false;
                                break;
                            case "2":
                                A=0;
                                check=false;
                                break;
                            case "0":
                                doMainMenu();
                                check=false;
                                break;
                            case "99":
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Error_wrong_command\n"+"Please_enter_again:");
                        
                        }
                    }
                    i += inputPage * A + ((i + 1) % inputPage);
                }
                    
                
                
            }
        }
        doSubMenu();
    }
    //3
    public void doShowByC() throws FileNotFoundException{
        int i =0;
        System.out.println("Catalogs:");
        String inputCatalog=null;
        for(i=0;i<num_catalog;i++){
            int j=97+i;
            System.out.printf("[%c].%s ",j,catalog[i]);
        }
        System.out.println();
        System.out.println("[0].Go_back_to_main_menu [99].Exit_system");
        while(true){
            
            System.out.println("Input_catalog_to_show:");
            String select = sc.next();
            if(select.equals("0")){
                doMainMenu();
                break;
            }
            if(select.equals("99")){
                System.exit(0);
            }
            if(select.length()!=1){
                
            }else{
                if(select.charAt(0)-97<=num_catalog-1){
                    inputCatalog = catalog[select.charAt(0)-97];
                    break;
                }
            }
            System.out.println("Error_wrong_data\n"+"Please_input_again:");
        }
        System.out.println("[ID] [Name]       [Phone]     [Catalog]    [Email]                  [BD]");
        for(i=0;i<d.num_data;i++){
            if(d.raw_data[i][3].equals(inputCatalog)){
                System.out.printf("%-4s %-12s %-11s %-12s %-24s %-4s",d.raw_data[i][0],d.raw_data[i][1],d.raw_data[i][2],d.raw_data[i][3],d.raw_data[i][4],d.raw_data[i][5]);
                System.out.println();
            }
        }
        doSubMenu();
    }
    //4
    public void doSearch() throws FileNotFoundException{
        
        System.out.println("Search by:\n"+"[1].ID [2].Name [3].Birthday\n"+"[0].Go_back_to_main_menu [99].Exit_system");
        
        int searchWay = 0;
        boolean cmdacc=false;
        while(!cmdacc){
            command = sc.next();
            switch(command){
                case "0":
                    doMainMenu();
                    break;
                case "99":
                    System.exit(0);
                case "1":
                    searchWay=0;
                    cmdacc=true;
                    break;
                case "2":
                    searchWay=1;
                    cmdacc=true;
                    break;
                case "3":
                    searchWay=5;
                    cmdacc=true;
                    break;
                default:
                    System.out.println("Error_wrong_data\n"+"Please_input_again:");
                    break;
                    
            }
        }

        System.out.println("Input_target:");
        while(true){
        String target = sc.next();
        boolean format =true;
        boolean found = false;
        int i =0;
        if(searchWay==0){
            if(target.length()>4){
                format = false;
            }
            for(int x =0;x<target.length();x++){
                if(target.charAt(x)<'0'||target.charAt(x)>'9'){
                    format = false;
                }
            }
        }else if(searchWay == 1){
            if(target.length()>12){
                format = false;
                
            }
            for(int x =0;x<target.length();x++){
                if(target.charAt(x)>'0'&&target.charAt(x)<'9'){
                    format = false;
                }
            }
        }
        else if(searchWay == 5){
            if(target.length()>4){
                format = false;
            }
            for(int x =0;x<target.length();x++){
                if(target.charAt(x)<'0'||target.charAt(x)>'9'){
                    format = false;
                }
            }
        }
        
        for( i=0;i<d.num_data;i++){
            
            if(searchWay==0){
                if(Integer.valueOf(target).equals(Integer.valueOf(d.data[i][0]))){
                    found = true;
                }
            }
            if(d.data[i][searchWay].equals(target)){
                found = true;
            }
        }
        if(format){
            if(found){
                System.out.println("Search_result:");
                System.out.println("[ID] [Name]       [Phone]     [Catalog]    [Email]                  [BD]");
                boolean forID=false;
                for( i=0;i<d.num_data;i++){
                    forID=false;
                    if(searchWay==0){
                        if(Integer.valueOf(target).equals(Integer.valueOf(d.data[i][0]))){
                            forID=true;
                        }
                    }
                    if(d.data[i][searchWay].equals(target)||forID){
                        System.out.printf("%-4s ",d.data[i][0]);
                        System.out.printf("%-12s ",d.data[i][1]);
                        System.out.printf("%-11s ",d.data[i][2]);
                        System.out.printf("%-12s ",d.data[i][3]);
                        System.out.printf("%-24s ",d.data[i][4]);
                        System.out.printf("%-4s",d.data[i][5]);
                        System.out.println();
                    }
                    
                }
    
                
            }
            else{
                System.out.println("Error_no_result");
                
            }
            System.out.println("[1].Restart_search [0].Go_back_to_main_menu [99].Exit_system");
            while(true){
                command=sc.next();
                switch(command){
                    case "1":
                        doSearch();
                        break;
                    case "0":
                        doMainMenu();
                        break;
                    
                    case "99":
                        System.exit(0);
        
                    default:
                        System.out.println("Error_wrong_command\n"+"Please_enter_again:");
                        break;
    
                }
            }
        }else{
            System.out.println("Error_wrong_data\n"+"Please_input_again:");
        }
        
        }
        
    }
    //5
    public void doMod() throws FileNotFoundException{
        
        
        int i=0;
        System.out.println("Input_ID_to_be_modified:");
        String inputID = null;
        int datalocate=0;
        while(true){
            inputID = sc.next();
            
            if(Integer.valueOf(inputID)>d.num_data||Integer.valueOf(inputID)<1){

            }
            else{
                for(i =0;i<d.num_data;i++){
                    if(Integer.valueOf(d.raw_data[i][0]).equals(Integer.valueOf(inputID))){
                        System.out.println("Search_result:");
                        System.out.println("[ID] [Name]       [Phone]     [Catalog]    [Email]                  [BD]");
                        System.out.printf("%-4s %-12s %-11s %-12s %-24s %-4s",d.raw_data[i][0],d.raw_data[i][1],d.raw_data[i][2],d.raw_data[i][3],d.raw_data[i][4],d.raw_data[i][5]);
                        System.out.println();
                        datalocate=i;
                    }
                }
                
                break;
            }
            System.out.println("Error_no_such_data");
        }
        String hehe2 = sc.nextLine();

        System.out.println("New_name:");
        String inputName=null;
        while(true){
            int check = 0;
            inputName = sc.nextLine();
            if(inputName.equals("")){
                inputName = d.raw_data[datalocate][1];
                break;
            }
            for(i=0;i<inputName.length();i++){
                if(inputName.charAt(i)>=48&&inputName.charAt(i)<=57){
                    check++;
                }
            }
            if(check==0&&inputName.length()<=12){
                break;
            }
            System.out.println("Error_wrong_data\n"+"Please_input_again:");
        }
        //phone
        System.out.println("New_phone:");
        String inputPhone=null;
        while(true){
            inputPhone = sc.nextLine();
            int check1 =0;
            int check2 =0;
            if(inputPhone.equals("")){
                inputPhone = d.raw_data[datalocate][2];
                break;
            }
            if(inputPhone.length()!=11){
                
            }else{
                for(i =0;i<inputPhone.length();i++){
                    if(i==4&&inputPhone.charAt(4)=='-'){
                        check1++;
                    }
                    else if(inputPhone.charAt(i)<=57||inputPhone.charAt(i)>=48){
                        check2++;
                    }
                }
            }
            if(check1==1&&check2==10){
                break;
            }
            System.out.println("Error_wrong_data\n"+"Please_input_again:");
        }
        //Catalog
        
        System.out.print("Catalogs:");
        String inputCatalog=null;
        for(i=0;i<num_catalog;i++){
            int j=97+i;
            System.out.printf("[%c].%s ",j,catalog[i]);
        }
        System.out.println();

        while(true){
            
            System.out.println("New_catalog:");
            String select = sc.nextLine();
            if(select.equals("")){
                inputCatalog = d.raw_data[datalocate][3];
                break;
            }
            if(select.length()!=1){
                
            }else{
                if(select.charAt(0)-97<=num_catalog-1){
                    inputCatalog = catalog[select.charAt(0)-97];
                    break;
                }
            }
            System.out.println("Error_wrong_data\n"+"Please_input_again:");
        }
        //email
        System.out.println("New_email:");
        String inputEmail=null;
        while(true){
            inputEmail = sc.nextLine();
            if(inputEmail.equals("")){
                inputEmail = d.raw_data[datalocate][4];
                break;
            }
            int check =0;
            int check2=0;
            if(inputEmail.length()>24){

            }else{
                for(i=0;i<inputEmail.length();i++){
                    if(inputEmail.charAt(i)=='@'){
                        check++;
                    }else if(inputEmail.charAt(i)=='.'){
                        check2++;
                    }
                }
            }
            if(check==1&&check2>=1){
                break;
            }
            System.out.println("Error_wrong_data\n"+"Please_input_again:");
        }
        //birthday
        System.out.println("New_birthday:");
        String inputBirthday=null;
        while(true){
            int check=0;
            int check2=0;
            inputBirthday= sc.nextLine();
            if(inputBirthday.equals("")){
                inputBirthday = d.raw_data[datalocate][5];
                break;
            }
            if(inputBirthday.length()!=4){

            }else{
                for(i=0;i<inputBirthday.length();i++){
                    if(inputBirthday.charAt(i)<48&&inputBirthday.charAt(i)>57){
                        check=0;
                    }
                }
                if(check==0){
                    int BD = Integer.valueOf(inputBirthday);
                    if(BD<101){
                        check2++;
                    }
                    if(BD>131&&BD<201){
                        check2++;
                    }
                    if(BD>229&&BD<301){
                        check2++;
                    }
                    if(BD>331&&BD<401){
                        check2++;
                    }
                    if(BD>430&&BD<501){
                        check2++;
                    }
                    if(BD>531&&BD<601){
                        check2++;
                    }
                    if(BD>630&&BD<701){
                        check2++;
                    }
                    if(BD>731&&BD<801){
                        check2++;
                    }
                    if(BD>831&&BD<901){
                        check2++;
                    }
                    if(BD>930&&BD<1001){
                        check2++;
                    }
                    if(BD>1031&&BD<1101){
                        check2++;
                    }
                    if(BD>1130&&BD<1201){
                        check2++;
                    }
                    if(BD>1231){
                        check2++;
                    }

                }
            }
            if(check2==0){
                break;
            }
            System.out.println("Error_wrong_data\n"+"Please_input_again:");
        }

        //input
        PrintWriter Data = new PrintWriter(new FileOutputStream("data.txt"));
        for(i=0;i<d.num_data;i++){
            if(i!=datalocate){
                Data.printf("%-4s %-12s %-11s %-12s %-24s %-4s",d.raw_data[i][0],d.raw_data[i][1],d.raw_data[i][2],d.raw_data[i][3],d.raw_data[i][4],d.raw_data[i][5]);
                Data.println();
            }else{
                Data.printf("%-4s %-12s %-11s %-12s %-24s %-4s",d.raw_data[i][0],inputName,inputPhone,inputCatalog,inputEmail,inputBirthday);
            }
        }
        Data.close();
        d.rawDataRenew();
        d.dataRenew();
        System.out.println("Modify_data_success");
        doSubMenu();
    }
    //6
    public void doDel() throws FileNotFoundException{
        int i=0;
        System.out.println("Input_ID_to_be_deleted:");
        String inputID = null;
        int datalocate=0;
        while(true){
            inputID = sc.next();
            if(Integer.valueOf(inputID)>d.num_data||Integer.valueOf(inputID)<1){

            }
            else{
                for(i =0;i<d.num_data;i++){
                    if(Integer.valueOf(d.raw_data[i][0]).equals(Integer.valueOf(inputID))){
                        datalocate=i;
                    }
                }
                
                break;
            }
            System.out.println("Error_no_such_data");
        }
        //input
        PrintWriter Data = new PrintWriter(new FileOutputStream("data.txt"));
        for(i=0;i<d.num_data;i++){
            if(i!=datalocate){
                Data.printf("%-4s %-12s %-11s %-12s %-24s %-4s",d.raw_data[i][0],d.raw_data[i][1],d.raw_data[i][2],d.raw_data[i][3],d.raw_data[i][4],d.raw_data[i][5]);
                Data.println();
            }
        }
        Data.close();
        d.rawDataRenew();
        d.dataRenew();
        System.out.println("Delete_data_success");
        doSubMenu();

        
    }
    //7 done
    public void doAddCont() throws FileNotFoundException{
        int i =0;
        //name
        System.out.println("Name:");
        String inputName=null;
        while(true){
            int check = 0;
            inputName = sc.next();
            for(i=0;i<inputName.length();i++){
                if(inputName.charAt(i)>=48&&inputName.charAt(i)<=57){
                    check++;
                }
            }
            if(check==0&&inputName.length()<=12){
                break;
            }
            System.out.println("Error_wrong_data\n"+"Please_input_again:");
        }
        //phone
        System.out.println("Phone_number:");
        String inputPhone=null;
        while(true){
            inputPhone = sc.next();
            int check1 =0;
            int check2 =0;
            if(inputPhone.length()!=11){
                
            }else{
                for(i =0;i<inputPhone.length();i++){
                    if(i==4&&inputPhone.charAt(4)=='-'){
                        check1++;
                    }
                    else if(inputPhone.charAt(i)<=57||inputPhone.charAt(i)>=48){
                        check2++;
                    }
                }
            }
            if(check1==1&&check2==10){
                break;
            }
            System.out.println("Error_wrong_data\n"+"Please_input_again:");
        }
        //Catalog
        
        System.out.print("Catalogs:");
        String inputCatalog=null;
        for(i=0;i<num_catalog;i++){
            int j=97+i;
            System.out.printf("[%c].%s ",j,catalog[i]);
        }
        System.out.println();

        while(true){
            
            System.out.println("Catalog:");
            String select = sc.next();
            
            if(select.length()!=1){
                
            }else{
                if(select.charAt(0)-97<=num_catalog-1){
                    inputCatalog = catalog[select.charAt(0)-97];
                    break;
                }
            }
            System.out.println("Error_wrong_data\n"+"Please_input_again:");
        }
        //email
        System.out.println("Email:");
        String inputEmail=null;
        while(true){
            inputEmail = sc.next();
            int check =0;
            int check2=0;
            if(inputEmail.length()>24){

            }else{
                for(i=0;i<inputEmail.length();i++){
                    if(inputEmail.charAt(i)=='@'){
                        check++;
                    }else if(inputEmail.charAt(i)=='.'){
                        check2++;
                    }
                }
            }
            if(check==1&&check2>=1){
                break;
            }
            System.out.println("Error_wrong_data\n"+"Please_input_again:");
        }
        //birthday
        System.out.println("Birthday:");
        String inputBirthday=null;
        while(true){
            int check=0;
            int check2=0;
            inputBirthday= sc.next();
            if(inputBirthday.length()!=4){

            }else{
                for(i=0;i<inputBirthday.length();i++){
                    if(inputBirthday.charAt(i)<48&&inputBirthday.charAt(i)>57){
                        check=0;
                    }
                }
                if(check==0){
                    int BD = Integer.valueOf(inputBirthday);
                    if(BD<101){
                        check2++;
                    }
                    if(BD>131&&BD<201){
                        check2++;
                    }
                    if(BD>229&&BD<301){
                        check2++;
                    }
                    if(BD>331&&BD<401){
                        check2++;
                    }
                    if(BD>430&&BD<501){
                        check2++;
                    }
                    if(BD>531&&BD<601){
                        check2++;
                    }
                    if(BD>630&&BD<701){
                        check2++;
                    }
                    if(BD>731&&BD<801){
                        check2++;
                    }
                    if(BD>831&&BD<901){
                        check2++;
                    }
                    if(BD>930&&BD<1001){
                        check2++;
                    }
                    if(BD>1031&&BD<1101){
                        check2++;
                    }
                    if(BD>1130&&BD<1201){
                        check2++;
                    }
                    if(BD>1231){
                        check2++;
                    }

                }
            }
            if(check2==0){
                break;
            }
            System.out.println("Error_wrong_data\n"+"Please_input_again:");
        }
        //input
        String ID=null;
        if(d.num_data>=0&&d.num_data<=9){
            ID = "000"+String.valueOf(d.num_data+1);
        }else if(d.num_data>=10&&d.num_data<=99){
            ID = "00"+String.valueOf(d.num_data+1);
        }else if(d.num_data>=100&&d.num_data<=999){
            ID = "0"+String.valueOf(d.num_data+1);
        }else if(d.num_data>=1000&&d.num_data<=9999){
            ID = String.valueOf(d.num_data+1);
        }
        d.num_data++;
        PrintWriter Data = new PrintWriter(new FileOutputStream("data.txt"));
        for(i=0;i<d.num_data;i++){
            if(i!=d.num_data-1){
                Data.printf("%-4s %-12s %-11s %-12s %-24s %-4s",d.raw_data[i][0],d.raw_data[i][1],d.raw_data[i][2],d.raw_data[i][3],d.raw_data[i][4],d.raw_data[i][5]);
                Data.println();
            }else{
                Data.printf("%-4s %-12s %-11s %-12s %-24s %-4s",ID,inputName,inputPhone,inputCatalog,inputEmail,inputBirthday);
            }
        }
        Data.close();
        d.rawDataRenew();
        d.dataRenew();
        System.out.println("Add_contact_success");
        doSubMenu();

        
    }
    //8
    public void doAddCatalog() throws FileNotFoundException {

        PrintWriter Catalog = new PrintWriter(new FileOutputStream("catalog.txt"));
        String[] temp = new String[100];
        for (int i = 0; i < num_catalog; i++) {
            temp[i] = catalog[i];
        }
        System.out.println("Please_input_new_catalog:");
        String newcatalog = sc.next();
        // modify
        String a = String.valueOf(newcatalog.charAt(0));
        newcatalog = newcatalog.substring(1);
        newcatalog = a.toUpperCase() + newcatalog;
        /// check whether same

        boolean same = false;
        for (int i = 0; i < num_catalog; i++) {///
            if (newcatalog.equals(temp[i])) {
                same = true;
            }
        }
        if (same) {
            System.out.println("Error_catalog_existed");
            Catalog.close();
            doAddCatalog();

        } else if (newcatalog.length() >= 12) {
            System.out.println("Error_catalog_too_long");
            Catalog.close();
            doAddCatalog();

        } else {

            temp[num_catalog] = newcatalog;

            /// compare
            for (int x = 0; x < num_catalog; x++) {
                for (int y = 0; y < num_catalog; y++) {
                    if (temp[y].compareTo(temp[y + 1]) > 0) {
                        String temp1 = temp[y];
                        temp[y] = temp[y + 1];
                        temp[y + 1] = temp1;
                    }
                }
            }
            for (int i = 0; i < num_catalog + 1; i++) {
                Catalog.println(temp[i]);
            }

            System.out.println("Add_catalog_" + newcatalog + "_success");
            Catalog.close();
            catalogRenew();

            doSubMenu();
        }

    }

    // 9
    public void doShowCatalog() throws FileNotFoundException {
        Scanner Catalog = new Scanner(new FileInputStream("catalog.txt"));
        String c;
        System.out.println("[Catalog]");
        while (Catalog.hasNext()) {
            c = Catalog.next();
            System.out.println(c);
        }
        doSubMenu();
    }
    //10
    public void doSetField() throws FileNotFoundException{
        int i=0;
        boolean check=true;
        System.out.printf("[1].Show_name:%d [2].Show_phone:%d [3].Show_cat:%d [4].Show_email:%d [5].Show_bd:%d",d.show[0],d.show[1],d.show[2],d.show[3],d.show[4]);
        System.out.println();
        for(i=0;i<5;i++){
            if(i==0)
            System.out.println("New_show_name(0/1):");
            if(i==1)
            System.out.println("New_show_phone(0/1):");
            if(i==2)
            System.out.println("New_show_cat(0/1):");
            if(i==3)
            System.out.println("New_show_email(0/1):");
            if(i==4)
            System.out.println("New_show_bd(0/1):");
            
            while(true){
                String change = sc.next();
                if(change.length()==1&&(change.charAt(0)>=48&&change.charAt(0)<=49)){
                    d.show[i]=Integer.valueOf(change);
                    
                    break;
                }
                else{
                    System.out.println("Input_error_please_input_0_or_1:");
                }
            }
            
            
        }
        System.out.printf("[1].Show_name:%d [2].Show_phone:%d [3].Show_cat:%d [4].Show_email:%d [5].Show_bd:%d",d.show[0],d.show[1],d.show[2],d.show[3],d.show[4]);
        System.out.println();
        d.doConfigUpdate();
        doSubMenu();
    }
    //11 default
    public void doSetPage() throws FileNotFoundException{
        System.out.println("show_defalt_perpage:"+d.page);
        System.out.println("new_show_defalt_perpage:");
        d.page=sc.nextInt();
        System.out.println("show_defalt_perpage:"+d.page);
        d.doConfigUpdate();
        doSubMenu();
    }
    //12
    public void doSetOrder() throws FileNotFoundException{
        if(d.order==1){
            System.out.println("show_sort_order:asc");
        }else{
            System.out.println("show_sort_order:des");
        }
        System.out.println("Please_input_new_sort_order:");
        boolean check=true;
        String Order = sc.next();
        while(check){
            switch(Order){
                case "asc":
                    d.order=1;
                    check=false;
                    break;
                case "des":
                    d.order=0;
                    check=false;
                    break;
                default:
                    System.out.println("Input_error_please_input_asc_or_des:");
                    Order = sc.next();
            }
        }

        if(d.order==1){
            System.out.println("show_sort_order:asc");
        }else{
            System.out.println("show_sort_order:des");
        }
        d.doConfigUpdate();
        d.dataRenew();
        doSubMenu();
    }
    //13
    public void doSerSort() throws FileNotFoundException{
        System.out.println("[1].ID [2].Name [3].Phone [4].Cat [5].Email [6].Bd\n"+"[0].Go_back_to_main_menu [99].Exit_system");
        int sort = sc.nextInt();
        switch(sort){
            case 1:
                d.sortField="id";
                System.out.println("Sorted_by:ID");
                break;
            case 2:
                d.sortField="name";
                System.out.println("Sorted_by:Name");
                break;
            case 3:
                d.sortField="phone";
                System.out.println("Sorted_by:Phone");
                break;
            case 4:
                d.sortField="catalog";
                System.out.println("Sorted_by:Cat");
                break;
            case 5:
                d.sortField="email";
                System.out.println("Sorted_by:Email");
                break;
            case 6:
                d.sortField="birthday";
                System.out.println("Sorted_by:Bd");
                break;
            case 0:
                doMainMenu();
                break;
            case 99:
                System.exit(0);

        }
        d.doConfigUpdate();
        d.showRenew();
        d.dataRenew();
        doSubMenu();
    }

    //14
    public void doShowRawData() throws FileNotFoundException{
        System.out.println("[ID] [Name]       [Phone]     [Catalog]    [Email]                  [BD]");
        for(int i=0;i<d.num_data;i++){
            for(int j=0;j<6;j++){
                if(j==0){
                    System.out.printf("%-4s ",d.raw_data[i][0]);
                }
                if(j==1){
                    System.out.printf("%-12s ",d.raw_data[i][1]);
                }
                if(j==2){
                    System.out.printf("%-11s ",d.raw_data[i][2]);
                }
                if(j==3){
                    System.out.printf("%-12s ",d.raw_data[i][3]);
                }
                if(j==4){
                    System.out.printf("%-24s ",d.raw_data[i][4]);
                }
                if(j==5){
                    System.out.printf("%-4s",d.raw_data[i][5]);
                }
            }
            System.out.println();
        }
        doSubMenu();
    }
    //15
    public void doOpt() throws FileNotFoundException{
        System.out.println("Please_confirm_data_optimize_y_or_n:");
        String cmd = sc.next();
        if(cmd.equals("y")){
            
            d.showRenew();
            d.dataRenew();
            PrintWriter pw = new PrintWriter(new FileOutputStream("data.txt"));
            for(int i=0;i<d.num_data;i++){
                for(int j=0;j<6;j++){
                    pw.print(d.data[i][j]+" ");
                }
                pw.println();
            }
            pw.close();
            d.rawDataRenew();
            System.out.println("Data_optimize_success");
        }else if(cmd.equals("n")){
            System.out.println("Data_optimize_denied");
        }
        doSubMenu();
    }
    //16
    public void doShowAccount()throws FileNotFoundException{    
        System.out.println("[Account]    [Password]");
        for(int i=0;i<num_account;i++){
            System.out.println(account[i][0]+"    "+account[i][1]);
        }
        doSubMenu();
    }
    //17
    public void doAddAccount() throws FileNotFoundException{
        System.out.println("New_account:");
        String newAccount = sc.next();
        System.out.println("New_password:");
        String newPwd = sc.next();

        PrintWriter pw = new PrintWriter(new FileOutputStream("account.txt"));
        account[num_account][0]=newAccount;
        account[num_account][1]=newPwd;
        num_account++;
        for(int i =0;i<num_account-1;i++){
            if(account[i][0].compareTo(account[i+1][0])>0){
                String[] temp = new String[2];
                temp[0]= account[i][0];
                temp[1]= account[i][1];
                account[i][0]=account[i+1][0];
                account[i][1]=account[i+1][1];
                account[i+1][0]=temp[0];
                account[i+1][1]=temp[1];
            }
        }

        //output
        for(int j=0;j<num_account;j++){
            pw.println(account[j][0]+" "+account[j][1]);
        }
        pw.close();
        accountRenew();
        doSubMenu();
        
    }
    //18
    public void doDeleAccount()throws FileNotFoundException{
        System.out.println("Delete_account:");
        while(true){
        String deleAccount=sc.next();
        int deleLocate=0;
        boolean deleFind=false;
        for(int i =0;i<num_account;i++){
            if(deleAccount.equals(account[i][0])){
                deleFind=true;
                deleLocate=i;
                break;
            }
        }

        if(deleFind){
            PrintWriter pw = new PrintWriter(new FileOutputStream("account.txt"));
            for(int y=0;y<num_account;y++){
                if(y!=deleLocate){
                    pw.println(account[y][0]+" "+account[y][1]);
                }
                
            }
            pw.close();
            System.out.println("Delete_account_success");
            accountRenew();
            doSubMenu();
        }
        else{
            System.out.println("No_account_please_try_again:");
            

        }
        }
    }
    //19
    public void doModAccount() throws FileNotFoundException{
        while(true){

            System.out.println("Modify_account:");
            String modAccount= sc.next();
            System.out.println("New_account:");
            String newAccount = sc.next();
            System.out.println("New_password:");
            String newPwd = sc.next();
            //find same account
            boolean sameAccount = false;
            int modAccountPosition=0;
            int i=0;
            for( i =0;i<num_account;i++){
                if(modAccount.equals(account[i][0])){
                    sameAccount = true;
                    modAccountPosition=i;
                }
            }
            
            
            if(!sameAccount){
                System.out.println("No_account_please_try_again:");
            }
            else{
                PrintWriter pw = new PrintWriter(new FileOutputStream("account.txt"));
                for(int y=0;y<num_account;y++){
                    if(y!=modAccountPosition){
                        pw.println(account[y][0]+" "+account[y][1]);
                    }
                    else{
                        pw.println(newAccount+" "+newPwd);
                    }
                }
                System.out.println("Modify_account_success");
                pw.close();
                accountRenew();
                doSubMenu();
            }
    
    
            }
    }
    // 20
    public void doLogOut() throws FileNotFoundException {
        System.out.println("Please_confirm_to_logout_y_or_n:");

        while (true) {
            command = sc.next();
            switch (command) {
                case "y":
                    doLogIn();
                    break;
                case "n":
                    doMainMenu();
                    break;
                default:
                    System.out.println("Error_input\nPlease_input_again:");
            }
        }
    }
}