package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String name;
    private String publisher;
    private String author;
    private String isbn;
}
