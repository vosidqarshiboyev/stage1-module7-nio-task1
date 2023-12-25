package com.epam.mjc.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class FileReader {

    public Profile getDataFromFile(File file) {
    Profile profile=new Profile();
        try(RandomAccessFile r=new RandomAccessFile(file,"r")){
            FileChannel fileChannel=r.getChannel();
            ByteBuffer byteBuffer=ByteBuffer.allocate(48);
            int c;
            StringBuilder s=new StringBuilder("");
            while((fileChannel.read(byteBuffer)>0)){
                byteBuffer.flip();
                for(int i=0;i<byteBuffer.limit();i++){
                    s.append((char) byteBuffer.get());
                }
                byteBuffer.clear();
            }
            String a=new String(s);
            a=a.replace(":","");
            a=a.replace("\n"," ");
            String[]result=a.split(" ");
            profile.setName(result[1]);
            profile.setAge(Integer.parseInt(result[3]));
            profile.setEmail(result[5]);
            profile.setPhone(Long.parseLong(result[7]));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return profile;
    }
}
