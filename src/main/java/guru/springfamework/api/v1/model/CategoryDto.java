package guru.springfamework.api.v1.model;

import lombok.*;

/**
 * Created by jt on 9/24/17.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private Long id;
    private String name;
}
