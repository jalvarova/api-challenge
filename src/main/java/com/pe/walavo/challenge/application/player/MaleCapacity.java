package com.pe.walavo.challenge.application.player;

import com.pe.walavo.challenge.infraestructure.dto.PlayerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaleCapacity implements CapacityPlayer {
    private PlayerDTO player;

    @Override
    public void measureCapacity() {
        player.setSkill(((player.getSkill() +
                player.getSpeed() +
                player.getStrength()) / 3) / RandomUtils.nextInt(1, 10));
    }
}
