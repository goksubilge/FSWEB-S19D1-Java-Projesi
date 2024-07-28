package com.wit.s191.dto;

import java.time.LocalDate;

public record MovieResponse(int id, String name, String directorName, int rating, LocalDate releaseDate) {
}
