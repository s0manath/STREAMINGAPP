package in.somanath.streamapp.playload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomMessage {
    private String message;
    private final boolean success=false;
}
