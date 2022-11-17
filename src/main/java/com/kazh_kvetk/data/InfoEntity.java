package com.kazh_kvetk.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class InfoEntity {
  private final Teacher teacher;
  private final Theme theme;
  private final Student student;
  private final Marks marks;
}