package com.content.extractor;

import com.content.model.QueryRequest;
import com.content.model.QueryResponse;
import com.content.service.ContentRestService;
import com.content.util.CSVUtil;
import com.content.util.FileUtil;
import com.content.util.ZipFileUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExtractorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExtractorService.class);

    @Value("${extractor.file.srcDir}")
    String srcDir;

    @Value("${extractor.file.destDir}")
    String destDir;

    @Value("${extractor.file.zipDir}")
    String zipDir;

    @Value("${extractor.file.extension}")
    private String fileExtension;

    private final ObjectMapper objectMapper = new ObjectMapper();

    ContentRestService contentRestService;

    @Scheduled(cron = "0 0/1 * * * *")
    public void process() {
        try {
            List<File> files = FileUtil.readFiles(srcDir, fileExtension);
            for (File file : files) {
                processQueryRequest(file);
            }
            ZipFileUtil.zipCSVFiles(destDir, zipDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processQueryRequest(File file) {
        try {
            List<QueryRequest> queryRequests = CSVUtil.getQueryRequestList(file);
            for (QueryRequest queryRequest : queryRequests) {
                LOGGER.info(":: QueryRequest :: {}", queryRequest);
                QueryResponse response = query(queryRequest);
                LOGGER.info(":: QueryResponse :: {}", response);
            }
            String destFile = destDir + File.separator + file.getName();
            FileUtil.moveFile(file, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private QueryResponse query(QueryRequest queryRequest) throws JsonProcessingException {
        return contentRestService.query(objectMapper.writeValueAsString(queryRequest));
    }

    public static void cleanUp(Path path) throws IOException {
        Files.delete(path);
    }

    @Autowired
    public void setContentRestService(ContentRestService contentRestService) {
        this.contentRestService = contentRestService;
    }

}
