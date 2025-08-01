package com.boussas.tasks.mappers;

import com.boussas.tasks.model.Tag;
import com.boussas.tasks.model.dto.TagDTO;

public interface TagMapper {
    TagDTO toDto(Tag tag);
    Tag fromDto(TagDTO tagDTO);
}
