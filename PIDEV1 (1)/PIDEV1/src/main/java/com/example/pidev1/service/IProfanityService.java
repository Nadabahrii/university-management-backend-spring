package com.example.pidev1.service;

import java.io.IOException;

public interface IProfanityService {
    boolean containsProfanity(String text) throws IOException;
}
