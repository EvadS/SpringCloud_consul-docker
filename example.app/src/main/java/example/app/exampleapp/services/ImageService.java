package example.app.exampleapp.services;


import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.annotation.PostConstruct;

import example.app.exampleapp.domain.Image;
import example.app.exampleapp.external.BrowserlessClient;
import example.app.exampleapp.repositories.ImageRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ImageService  {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private BrowserlessClient browserlessClient;

    private String getLargestImageScript;

    @PostConstruct
    public void initialize() throws IOException {
        getLargestImageScript = IOUtils.toString(getClass().getResourceAsStream("/getLargestImage.js"), StandardCharsets.UTF_8.name());
    }

    public Image findLargestImage(String url) {
        BrowserlessClient.BrowserlessContext browserlessContext = new BrowserlessClient.BrowserlessContext(url);
        BrowserlessClient.LargestImageRequest largestImageRequest = new BrowserlessClient.LargestImageRequest(getLargestImageScript, browserlessContext);

        BrowserlessClient.ImageInfo imageInfo = browserlessClient.findLargestImage(largestImageRequest);

        Image image = new Image();

        image.setSourceUrl(url);
        image.setImageUrl(imageInfo.getUrl());

        return imageRepository.save(image);
    }
}
