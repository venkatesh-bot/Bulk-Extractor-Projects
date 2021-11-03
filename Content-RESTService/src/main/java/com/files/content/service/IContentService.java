package com.files.content.service;

import com.files.content.model.QueryResponse;

public interface IContentService {

    QueryResponse search(String requestJson);

}
