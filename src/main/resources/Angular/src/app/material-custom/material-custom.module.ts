import { NgModule } from '@angular/core';
import {
  MatButtonModule,
  MatButtonToggleModule,
  MatBadgeModule,
  MatIconModule,
  MatProgressSpinnerModule,
  MatToolbarModule,
  MatSidenavModule,
  MatMenuModule,
  MatListModule,
  MatDividerModule,
  MatExpansionModule,
  MatTabsModule,
  MatCardModule,
  MatFormFieldModule,
  MatInputModule,
  MatSelectModule,
  MatAutocompleteModule,
  MatCheckboxModule,
  MatRadioModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatTooltipModule,
  MatSnackBarModule,
  MatDialogModule,
  MatTableModule,
  MatSortModule,
  MatPaginatorModule,
  MatSliderModule,
  MatProgressBarModule,
  MatBottomSheetModule,
  MatStepperModule,
  MatSlideToggleModule,
  MatGridListModule,
} from '@angular/material';

const MaterialComponentsUsed = [
  MatButtonModule,
  MatButtonToggleModule,
  MatBadgeModule,
  MatIconModule,
  MatProgressSpinnerModule,
  MatToolbarModule,
  MatSidenavModule,
  MatMenuModule,
  MatListModule,
  MatDividerModule,
  MatGridListModule,
  MatExpansionModule,
  MatTabsModule,
  MatCardModule,
  MatStepperModule,
  MatFormFieldModule,
  MatInputModule,
  MatSelectModule,
  MatAutocompleteModule,
  MatCheckboxModule,
  MatRadioModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatTooltipModule,
  MatSnackBarModule,
  MatDialogModule,
  MatTableModule,
  MatSortModule,
  MatPaginatorModule,
  MatSliderModule,
  MatProgressBarModule,
  MatBottomSheetModule,
  MatIconModule,
  MatListModule,
  MatBottomSheetModule,
  MatTabsModule,
  MatBadgeModule,
  MatStepperModule,
  MatSlideToggleModule,
  MatTableModule,
  MatGridListModule
];

@NgModule({
  imports: [MaterialComponentsUsed],
  exports: [MaterialComponentsUsed]
})
export class MaterialCustomModule { }
