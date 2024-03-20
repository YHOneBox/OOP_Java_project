import java.util.*;
import java.io.*;
public class Data{
    public int num_data;
    public String[][] raw_data=new String[100][6];
    public String[][] data = new String[100][6];
    public int[] show= new int[5];
    public int order ;
    public String sortField ;
    public int page;
    public Data()throws FileNotFoundException{
        rawDataRenew();
        showRenew();
        dataRenew();
    }
    public void rawDataRenew()throws FileNotFoundException{
        Scanner Data = new Scanner(new FileInputStream("data.txt"));
        int i=0,j=0;
        while(Data.hasNext()){
            if(j==6){
                i++;
                j=0;
            }
            raw_data[i][j]=Data.next();
            j++;
        }
        num_data=i+1;
    }
    public void dataRenew()throws FileNotFoundException{
        
        Scanner Config = new Scanner(new FileInputStream("config.txt"));
        while(Config.hasNext()){
            String temp = Config.next();
            if(temp.equals("show_sort_order:")){
                temp=Config.next();
                if(temp.equals("asc")){
                    order=1;
                }
                else{
                    order=0;
                }
            }
        }
        int x=0,y=0,k=0;
        if(sortField.equals("id")){
            k=0;
        }
        if(sortField.equals("name")){
            k=1;
        }
        if(sortField.equals("phone")){
            k=2;
        }
        if(sortField.equals("catalog")){
            k=3;
        }
        if(sortField.equals("email")){
            k=4;
        }
        if(sortField.equals("birthday")){
            k=5;
        }
        
        ///
        
        for(x=0;x<num_data+1;x++){
                data[x][k]=raw_data[x][k];
        }

        if(order==1){
            String temp=null;
            for(x=0;x<num_data-1;x++){
                for(y=0;y<num_data-1;y++){
                    if(data[y][k].compareTo(data[y+1][k])>0){
                        temp = data[y][k];
                        data[y][k]=data[y+1][k];
                        data[y+1][k]=temp;
                    }
                }
            }
        }
        else{
            String temp=null;
            for(x=0;x<num_data-1;x++){
                for(y=0;y<num_data-1;y++){
                    if(data[y+1][k].compareTo(data[y][k])>0){
                        temp = data[y+1][k];
                        data[y+1][k]=data[y][k];
                        data[y][k]=temp;
                    }
                }
            }
        }

        //final

        for(x=0;x<num_data;x++){
            for(y=0;y<num_data;y++){
                if(data[x][k].equals(raw_data[y][k])){
                    data[x][0]=raw_data[y][0];
                    data[x][1]=raw_data[y][1];
                    data[x][2]=raw_data[y][2];
                    data[x][3]=raw_data[y][3];
                    data[x][4]=raw_data[y][4];
                    data[x][5]=raw_data[y][5];
                }
            }
        }
    }
    public void showRenew() throws FileNotFoundException{
        Scanner Config = new Scanner(new FileInputStream("config.txt"));
        while(Config.hasNext()){
            String temp = Config.next();
            if(temp.equals("show_name:")){
                temp = Config.next();
                if(temp.equals("true")){
                    show[0]=1;
                }
                else{
                    show[0]=0;
                }
            }
            if(temp.equals("show_phone:")){
                temp = Config.next();
                if(temp.equals("true")){
                    show[1]=1;
                }
                else{
                    show[1]=0;
                }
            }
            if(temp.equals("show_catalog:")){
                temp = Config.next();
                if(temp.equals("true")){
                    show[2]=1;
                }
                else{
                    show[2]=0;
                }
            }
            if(temp.equals("show_email:")){
                temp = Config.next();
                if(temp.equals("true")){
                    show[3]=1;
                }
                else{
                    show[3]=0;
                }
            }
            if(temp.equals("show_birthday:")){
                temp = Config.next();
                if(temp.equals("true")){
                    show[4]=1;
                }
                else{
                    show[4]=0;
                }
            }
            if(temp.equals("show_sort_order:")){
                temp = Config.next();
                if(temp.equals("asc")){
                    order=1;
                }
                else{
                    order=0;
                }
            }
            if(temp.equals("show_sort_field:")){
                sortField = Config.next();
                
            }
            if(temp.equals("show_defalt_perpage:")){
                page=Config.nextInt();
                
            }
            
        }
    }
    public void doConfigUpdate() throws FileNotFoundException{
        Scanner Config = new Scanner(new FileInputStream("config.txt"));
        String[][] temp=new String[10][2];
        int i=0,j=0;
        while(Config.hasNext()){
            if(j==2){
                i++;
                j=0;
            }
            temp[i][j] = Config.next();
            j++;
        }
        Config.close();
        PrintWriter pw = new PrintWriter(new FileOutputStream("config.txt"));
        for(i=0;i<10;i++){
            pw.print(temp[i][0]+" ");
            if(temp[i][0].equals("show_name:")){
                if(show[0]==1){
                    pw.print("true");
                }
                else{
                    pw.print("false");
                }
            }
            else if(temp[i][0].equals("show_phone:")){
                if(show[1]==1){
                    pw.print("true");
                }
                else{
                    pw.print("false");
                }
            }
            else if(temp[i][0].equals("show_catalog:")){
                if(show[2]==1){
                    pw.print("true");
                }
                else{
                    pw.print("false");
                }
            }
            else if(temp[i][0].equals("show_email:")){
                if(show[3]==1){
                    pw.print("true");
                }
                else{
                    pw.print("false");
                }
            }
            else if(temp[i][0].equals("show_birthday:")){
                if(show[4]==1){
                    pw.print("true");
                }
                else{
                    pw.print("false");
                }
            }
            else if(temp[i][0].equals("show_sort_order:")){
                if(order==1){
                    pw.print("asc");
                }
                else{
                    pw.print("des");
                }
            }
            else if(temp[i][0].equals("show_sort_field:")){
                
                    pw.print(sortField);
                
            }
            else if(temp[i][0].equals("show_defalt_perpage:")){
                
                pw.print(page);
            
            }   
            
            
            else{
                pw.print(temp[i][1]);
            }
            pw.println();
        }
        pw.close();
    }
}