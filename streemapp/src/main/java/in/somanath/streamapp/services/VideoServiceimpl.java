package in.somanath.streamapp.services;

import in.somanath.streamapp.entities.Video;
import in.somanath.streamapp.repository.VideoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class VideoServiceimpl implements VideoService{

    @Value("${files.video}")
    String DIR;
    @Autowired
    private VideoRepository videoRepository;
    @PostConstruct
    public void init(){
        File file =new File(DIR);

        if(!file.exists()){
            file.mkdir();
            System.out.println("Folder  Created");

        }else{
            System.out.println("Folder Allready Created");
        }
    }
    @Override
    public Video save(Video video, MultipartFile file) {
        //original file name

        try{
            String filename= file.getOriginalFilename();
            String contentType=file.getContentType();
            InputStream inputStream=file.getInputStream();
            //folder path created
            String cleanFileName= StringUtils.cleanPath(filename);
            String cleanFolder=StringUtils.cleanPath(DIR);

            Path path = Paths.get(cleanFolder, cleanFileName);

            System.out.println(contentType);
            System.out.println(path);
            //folder path with file name

            //copy file to the folder
            Files.copy(inputStream,path, StandardCopyOption.REPLACE_EXISTING);

            //video meta data
            video.setContentType(contentType);
            video.setFilePath(path.toString());
            //metadata save

           return videoRepository.save(video);
        }catch (IOException e){
            e.printStackTrace();
            return  null;
        }

    }

    @Override
    public Video get(String videoId) {

        Video video = videoRepository.findById(videoId).orElseThrow(() -> new RuntimeException("video not found"));
        return video;
    }

    @Override
    public Video getByTitle(String title) {
        return null;
    }

    @Override
    public List<Video> getAll() {
        return videoRepository.findAll();
    }
}
