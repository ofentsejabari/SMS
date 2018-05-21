package schooladministration;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.DialogUI;
import entry.HSpacer;
import entry.SMS;
import static entry.SMS.getGraphics;
import entry.ToolTip;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;
import mysqldriver.AdminQuery;

/**
 *
 * @author jabari
 */
public class UpdateGradingScheme extends JFXDialog{

    private final JFXTextField aStarLowerBound, aStarUpperBound, aStarPoints,
            aLowerBound, aUpperBound, aPoints, 
            bLowerBound, bUpperBound, bPoints,
            cLowerBound, cUpperBound, cPoints,
            dLowerBound, dUpperBound, dPoints,
            eLowerBound, eUpperBound, ePoints,
            fLowerBound, fUpperBound, fPoints,
            uLowerBound, uUpperBound, uPoints;
    
    private ObservableList<GradeScheme> grades = null;

    public UpdateGradingScheme() {
            
        StackPane stackPane = new StackPane();
        BorderPane container = new BorderPane();
        stackPane.getChildren().add(container);
        
        //-- Toolbar -----------------------------------------------------------
        HBox toolBar = new HBox();
        toolBar.getStyleClass().add("screen-decoration");
        
        JFXButton btn_close = new JFXButton("", getGraphics(MaterialDesignIcon.WINDOW_CLOSE, "close", 20));
        btn_close.getStyleClass().add("window-close");
        btn_close.setOnAction((ActionEvent event) -> {
            close();
        });
        
        Label title = new Label("Update Stream Gradings");
        title.getStyleClass().add("window-title");
        
        toolBar.getChildren().addAll(title, new HSpacer(), btn_close);
        container.setTop(toolBar);
        
        /****************************** Content *******************************/
        container.getStyleClass().addAll("container");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.getStyleClass().addAll("container");
        grid.setHgap(10);
        grid.setVgap(10);
        
        grid.add(new Label("LOWER BOUND"), 1, 0);
        grid.add(new Label("UPPER BOUND"), 3, 0);
        
        grid.add(new Label("A*"), 0, 1);
        aStarLowerBound = new JFXTextField();
        aStarLowerBound.setAlignment(Pos.CENTER);
        aStarLowerBound.setText("0");
        aStarLowerBound.setPrefWidth(100);
        grid.add(aStarLowerBound, 1, 1);
        validate(aStarLowerBound);
        
        Label aStarSeparetor = new Label("-");
        aStarSeparetor.setStyle("-fx-font-size:22; -fx-font-weight:bold;");
        grid.add(aStarSeparetor, 2, 1);
        
        aStarUpperBound = new JFXTextField();
        aStarUpperBound.setAlignment(Pos.CENTER);
        aStarUpperBound.setText("0");
        aStarUpperBound.setPrefWidth(100);
        grid.add(aStarUpperBound, 3, 1);
        validate(aStarUpperBound);
        
        
        Label aStarSeparetor2 = new Label("-");
        aStarSeparetor2.setStyle("-fx-font-size:22; -fx-font-weight:bold;");
        grid.add(aStarSeparetor2, 4, 1);
        
        aStarPoints = new JFXTextField();
        aStarPoints.setAlignment(Pos.CENTER);
        aStarPoints.setText("0");
        aStarPoints.setPrefWidth(100);
        grid.add(aStarPoints, 5, 1);
        validate(aStarPoints);
        
        /**********************************************************************/
        grid.add(new Label("A"), 0, 2);
        aLowerBound = new JFXTextField();
        aLowerBound.setAlignment(Pos.CENTER);
        aLowerBound.setText("0");
        aLowerBound.setPrefWidth(100);
        grid.add(aLowerBound, 1, 2);
        validate(aLowerBound);
        
        Label aSeparetor = new Label("-");
        aSeparetor.setStyle("-fx-font-size:22; -fx-font-weight:bold;");
        grid.add(aSeparetor, 2, 2);
        
        aUpperBound = new JFXTextField();
        aUpperBound.setAlignment(Pos.CENTER);
        aUpperBound.setText("0");
        aUpperBound.setPrefWidth(100);
        grid.add(aUpperBound, 3, 2);
        validate(aUpperBound);
        
        Label aSeparetor2 = new Label("-");
        aSeparetor2.setStyle("-fx-font-size:22; -fx-font-weight:bold;");
        grid.add(aSeparetor2, 4, 2);
        
        aPoints = new JFXTextField();
        aPoints .setAlignment(Pos.CENTER);
        aPoints .setText("0");
        aPoints .setPrefWidth(100);
        grid.add(aPoints , 5, 2);
        validate(aPoints );
        
        /**********************************************************************/
        grid.add(new Label("B"), 0, 3);
        bLowerBound = new JFXTextField();
        bLowerBound.setAlignment(Pos.CENTER);
        bLowerBound.setText("0");
        bLowerBound.setPrefWidth(100);
        grid.add(bLowerBound, 1, 3);
        validate(bLowerBound);
        
        Label bSeparetor = new Label("-");
        bSeparetor.setStyle("-fx-font-size:22; -fx-font-weight:bold;");
        grid.add(bSeparetor, 2, 3);
        
        bUpperBound = new JFXTextField();
        bUpperBound.setAlignment(Pos.CENTER);
        bUpperBound.setText("0");
        bUpperBound.setPrefWidth(100);
        grid.add(bUpperBound, 3, 3);
        validate(bUpperBound);
        
        Label bSeparetor2 = new Label("-");
        bSeparetor2.setStyle("-fx-font-size:22; -fx-font-weight:bold;");
        grid.add(bSeparetor2, 4, 3);
        
        bPoints = new JFXTextField();
        bPoints.setAlignment(Pos.CENTER);
        bPoints.setText("0");
        bPoints.setPrefWidth(100);
        grid.add(bPoints, 5, 3);
        validate(bPoints);
        
        /**********************************************************************/
        grid.add(new Label("C"), 0, 4);
        cLowerBound = new JFXTextField();
        cLowerBound.setAlignment(Pos.CENTER);
        cLowerBound.setText("0");
        cLowerBound.setPrefWidth(100);
        grid.add(cLowerBound, 1, 4);
        validate(cLowerBound);
        
        Label cSeparetor = new Label("-");
        cSeparetor.setStyle("-fx-font-size:22; -fx-font-weight:bold;");
        grid.add(cSeparetor, 2, 4);
        
        cUpperBound = new JFXTextField();
        cUpperBound.setAlignment(Pos.CENTER);
        cUpperBound.setText("0");
        cUpperBound.setPrefWidth(100);
        grid.add(cUpperBound, 3, 4);
        validate(cUpperBound);
        
        Label cSeparetor2 = new Label("-");
        cSeparetor2.setStyle("-fx-font-size:22; -fx-font-weight:bold;");
        grid.add(cSeparetor2, 4, 4);
        
        cPoints = new JFXTextField();
        cPoints.setAlignment(Pos.CENTER);
        cPoints.setText("0");
        cPoints.setPrefWidth(100);
        grid.add(cPoints, 5, 4);
        validate(cPoints);
        
        /**********************************************************************/
        grid.add(new Label("D"), 0, 5);
        dLowerBound = new JFXTextField();
        dLowerBound.setAlignment(Pos.CENTER);
        dLowerBound.setText("0");
        dLowerBound.setPrefWidth(100);
        grid.add(dLowerBound, 1, 5);
        validate(dLowerBound);
        
        Label dSeparetor = new Label("-");
        dSeparetor.setStyle("-fx-font-size:22; -fx-font-weight:bold;");
        grid.add(dSeparetor, 2, 5);
        
        dUpperBound = new JFXTextField();
        dUpperBound.setAlignment(Pos.CENTER);
        dUpperBound.setText("0");
        dUpperBound.setPrefWidth(100);
        grid.add(dUpperBound, 3, 5);
        validate(dUpperBound);
        
        Label dSeparetor2 = new Label("-");
        dSeparetor2.setStyle("-fx-font-size:22; -fx-font-weight:bold;");
        grid.add(dSeparetor2, 4, 5);
        
        dPoints = new JFXTextField();
        dPoints.setAlignment(Pos.CENTER);
        dPoints.setText("0");
        dPoints.setPrefWidth(100);
        grid.add(dPoints, 5, 5);
        validate(dPoints);
        
        /**********************************************************************/
        grid.add(new Label("E"), 0, 6);
        eLowerBound = new JFXTextField();
        eLowerBound.setAlignment(Pos.CENTER);
        eLowerBound.setText("0");
        eLowerBound.setPrefWidth(100);
        grid.add(eLowerBound, 1, 6);
        validate(eLowerBound);
        
        Label eSeparetor = new Label("-");
        eSeparetor.setStyle("-fx-font-size:22; -fx-font-weight:bold;");
        grid.add(eSeparetor, 2, 6);
        
        eUpperBound = new JFXTextField();
        eUpperBound.setAlignment(Pos.CENTER);
        eUpperBound.setText("0");
        eUpperBound.setPrefWidth(100);
        grid.add(eUpperBound, 3, 6);
        validate(eUpperBound);
        
        Label eSeparetor2 = new Label("-");
        eSeparetor2.setStyle("-fx-font-size:22; -fx-font-weight:bold;");
        grid.add(eSeparetor2, 4, 6);
        
        ePoints = new JFXTextField();
        ePoints.setAlignment(Pos.CENTER);
        ePoints.setText("0");
        ePoints.setPrefWidth(100);
        grid.add(ePoints, 5, 6);
        validate(ePoints);
        
        /**********************************************************************/
        grid.add(new Label("F"), 0, 7);
        fLowerBound = new JFXTextField();
        fLowerBound.setAlignment(Pos.CENTER);
        fLowerBound.setText("0");
        fLowerBound.setPrefWidth(100);
        grid.add(fLowerBound, 1, 7);
        validate(fLowerBound);
        
        Label fSeparetor = new Label("-");
        fSeparetor.setStyle("-fx-font-size:22; -fx-font-weight:bold;");
        grid.add(fSeparetor, 2, 7);
        
        fUpperBound = new JFXTextField();
        fUpperBound.setAlignment(Pos.CENTER);
        fUpperBound.setText("0");
        fUpperBound.setPrefWidth(100);
        grid.add(fUpperBound, 3, 7);
        validate(fUpperBound);
        
        Label fSeparetor2 = new Label("-");
        fSeparetor2.setStyle("-fx-font-size:22; -fx-font-weight:bold;");
        grid.add(fSeparetor2, 4, 7);
        
        fPoints = new JFXTextField();
        fPoints.setAlignment(Pos.CENTER);
        fPoints.setText("0");
        fPoints.setPrefWidth(100);
        grid.add(fPoints, 5, 7);
        validate(fPoints);
        
        /**********************************************************************/
        grid.add(new Label("U"), 0, 8);
        uLowerBound = new JFXTextField();
        uLowerBound.setAlignment(Pos.CENTER);
        uLowerBound.setText("0");
        uLowerBound.setPrefWidth(100);
        grid.add(uLowerBound, 1, 8);
        validate(uLowerBound);
        
        Label uSeparetor = new Label("-");
        uSeparetor.setStyle("-fx-font-size:22; -fx-font-weight:bold;");
        grid.add(uSeparetor, 2, 8);
        
        uUpperBound = new JFXTextField();
        uUpperBound.setAlignment(Pos.CENTER);
        uUpperBound.setText("0");
        uUpperBound.setPrefWidth(100);
        grid.add(uUpperBound, 3, 8);
        validate(uUpperBound);
        
        Label uSeparetor2 = new Label("-");
        uSeparetor2.setStyle("-fx-font-size:22; -fx-font-weight:bold;");
        grid.add(uSeparetor2, 4, 8);
        
        uPoints = new JFXTextField();
        uPoints.setAlignment(Pos.CENTER);
        uPoints.setText("0");
        uPoints.setPrefWidth(100);
        grid.add(uPoints, 5, 8);
        validate(uPoints);
        
        /**********************************************************************/
        
        //---
        grades = AdminQuery.getGrades();
        if(grades.size() > 0){
            for(GradeScheme grade: grades){
                if(grade.getSymbol().equalsIgnoreCase("A*")){
                    aStarLowerBound.setText(grade.getLowerBound());
                    aStarUpperBound.setText(grade.getUpperBound());
                    aStarPoints.setText(grade.getPoints());
                    
                }else if(grade.getSymbol().equalsIgnoreCase("A")){
                    aLowerBound.setText(grade.getLowerBound());
                    aUpperBound.setText(grade.getUpperBound());
                    aPoints.setText(grade.getPoints());
                    
                }else if(grade.getSymbol().equalsIgnoreCase("B")){
                    bLowerBound.setText(grade.getLowerBound());
                    bUpperBound.setText(grade.getUpperBound());
                    bPoints.setText(grade.getPoints());
                    
                }else if(grade.getSymbol().equalsIgnoreCase("C")){
                    cLowerBound.setText(grade.getLowerBound());
                    cUpperBound.setText(grade.getUpperBound());
                    cPoints.setText(grade.getPoints());
                    
                }else if(grade.getSymbol().equalsIgnoreCase("D")){
                    dLowerBound.setText(grade.getLowerBound());
                    dUpperBound.setText(grade.getUpperBound());
                    dPoints.setText(grade.getPoints());
                    
                }else if(grade.getSymbol().equalsIgnoreCase("E")){
                    eLowerBound.setText(grade.getLowerBound());
                    eUpperBound.setText(grade.getUpperBound());
                    ePoints.setText(grade.getPoints());
                    
                }else if(grade.getSymbol().equalsIgnoreCase("F")){
                    fLowerBound.setText(grade.getLowerBound());
                    fUpperBound.setText(grade.getUpperBound());
                    fPoints.setText(grade.getPoints());
                    
                }else if(grade.getSymbol().equalsIgnoreCase("U")){
                    uLowerBound.setText(grade.getLowerBound());
                    uUpperBound.setText(grade.getUpperBound());
                    uPoints.setText(grade.getPoints());
                    
                }
                
            }
        }
        
        JFXButton save = new JFXButton("Save");
        save.getStyleClass().add("dark-blue");
        save.setTooltip(new ToolTip("Update Grading Scheme"));
        save.setOnAction((ActionEvent event) -> {
            if(validate()){
                
                if(grades.size() > 0){
                    for(GradeScheme grade: grades){
                        if(grade.getSymbol().equalsIgnoreCase("A*")){
                            grade.setLowerBound(aStarLowerBound.getText().trim());
                            grade.setUpperBound(aStarUpperBound.getText().trim());
                            grade.setPoints(aStarPoints.getText().trim());

                        }else if(grade.getSymbol().equalsIgnoreCase("A")){
                            grade.setLowerBound(aLowerBound.getText().trim());
                            grade.setUpperBound(aUpperBound.getText().trim());
                            grade.setPoints(aPoints.getText().trim());

                        }else if(grade.getSymbol().equalsIgnoreCase("B")){
                            grade.setLowerBound(bLowerBound.getText().trim());
                            grade.setUpperBound(bUpperBound.getText().trim());
                            grade.setPoints(bPoints.getText().trim());

                        }else if(grade.getSymbol().equalsIgnoreCase("C")){
                            grade.setLowerBound(cLowerBound.getText().trim());
                            grade.setUpperBound(cUpperBound.getText().trim());
                            grade.setPoints(cPoints.getText().trim());

                        }else if(grade.getSymbol().equalsIgnoreCase("D")){
                            grade.setLowerBound(dLowerBound.getText().trim());
                            grade.setUpperBound(dUpperBound.getText().trim());
                            grade.setPoints(dPoints.getText().trim());

                        }else if(grade.getSymbol().equalsIgnoreCase("E")){
                            grade.setLowerBound(eLowerBound.getText().trim());
                            grade.setUpperBound(eUpperBound.getText().trim());
                            grade.setPoints(ePoints.getText().trim());

                        }else if(grade.getSymbol().equalsIgnoreCase("F")){
                            grade.setLowerBound(fLowerBound.getText().trim());
                            grade.setUpperBound(fUpperBound.getText().trim());
                            grade.setPoints(fPoints.getText().trim());

                        }else if(grade.getSymbol().equalsIgnoreCase("U")){
                            grade.setLowerBound(uLowerBound.getText().trim());
                            grade.setUpperBound(uUpperBound.getText().trim());
                            grade.setPoints(uPoints.getText().trim());

                        }

                    }
                    
                    if(AdminQuery.updateGrades(grades, true)){
                        
                        new DialogUI("Stream grading scheme has been updated successfully",
                        DialogUI.SUCCESS_NOTIF, stackPane, this).show();
                        SchoolAdministartion.streamClassesController.streamGrading.gradeSchemeWorkService.restart();
                        
                    }else{
                        new DialogUI("Exception occurred while trying to update student grade details",
                        DialogUI.SUCCESS_NOTIF, stackPane, this).show();
                    }
                }else{
                
                    grades.add(new GradeScheme("0", "A*", aStarLowerBound.getText().trim(),
                            aStarUpperBound.getText().trim(), aStarPoints.getText().trim()));

                    grades.add(new GradeScheme("0", "A", aLowerBound.getText().trim(),
                            aUpperBound.getText().trim(), aPoints.getText().trim()));

                    grades.add(new GradeScheme("0", "B", bLowerBound.getText().trim(),
                            bUpperBound.getText().trim(), bPoints.getText().trim()));
                    
                    grades.add(new GradeScheme("0", "C", cLowerBound.getText().trim(),
                            cUpperBound.getText().trim(), cPoints.getText().trim()));
                    
                    grades.add(new GradeScheme("0", "D", dLowerBound.getText().trim(),
                            dUpperBound.getText().trim(), dPoints.getText().trim()));
                    
                    grades.add(new GradeScheme("0", "E", eLowerBound.getText().trim(),
                            eUpperBound.getText().trim(), ePoints.getText().trim()));
                    
                    grades.add(new GradeScheme("0", "F", fLowerBound.getText().trim(),
                            fUpperBound.getText().trim(), fPoints.getText().trim()));
                    
                    grades.add(new GradeScheme("0", "U", uLowerBound.getText().trim(),
                            uUpperBound.getText().trim(), uPoints.getText().trim()));
                    
                    if(AdminQuery.updateGrades(grades, false)){
                        new DialogUI("Stream grading scheme has been updated successfully",
                        DialogUI.SUCCESS_NOTIF, stackPane, this).show();
                        
                        SchoolAdministartion.streamClassesController.streamGrading.gradeSchemeWorkService.restart();
                    }else{
                        new DialogUI("Exception occurred while trying to update student grade details",
                        DialogUI.SUCCESS_NOTIF, stackPane, this).show();
                    }
                }
            }else{
                new DialogUI("Ensure that mandatory field are filled up... ",
                        DialogUI.SUCCESS_NOTIF, stackPane, this).show();
            }
            
        });
        
        container.setCenter(SMS.setBorderContainer(grid, null));
        
        //-- footer ------------------------------------------------------------
        HBox footer = new HBox(save);
        footer.getStyleClass().add("secondary-toolbar");
        container.setBottom(footer);

        //-- Set jfxdialog view  -----------------------------------------------
        setDialogContainer(SchoolAdministartion.ADMIN_MAN_STACK);
        setContent(stackPane);
        setOverlayClose(false);
        stackPane.setPrefSize(450, 200);
        show();
    }

    
    
    private void validate(JFXTextField field) {
        field.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if(!"".equals(newValue.trim())){
                try {
                    double am = Double.parseDouble(newValue.trim());
                    //field.setText(""+am);
                }catch (IllegalArgumentException e) {
                    field.setText("0");
                }
            }else{
                //field.setText("0");
            }
        });
    }
    
    private boolean validate(){
        if("".equals(aStarLowerBound.getText().trim())){aStarLowerBound.setText("0");}
        if("".equals(aStarUpperBound.getText().trim())){aStarUpperBound.setText("0");}
        if("".equals(aStarPoints.getText().trim())){aStarPoints.setText("0");}
        
        if("".equals(aLowerBound.getText().trim())){aLowerBound.setText("0");}
        if("".equals(aUpperBound.getText().trim())){aUpperBound.setText("0");}
        if("".equals(aPoints.getText().trim())){aPoints.setText("0");}
        
        if("".equals(bLowerBound.getText().trim())){bLowerBound.setText("0");}
        if("".equals(bUpperBound.getText().trim())){bUpperBound.setText("0");}
        if("".equals(bPoints.getText().trim())){bPoints.setText("0");}
      
        if("".equals(cLowerBound.getText().trim())){cLowerBound.setText("0");}
        if("".equals(cUpperBound.getText().trim())){cUpperBound.setText("0");}
        if("".equals(cPoints.getText().trim())){cPoints.setText("0");}
        
        if("".equals(dLowerBound.getText().trim())){dLowerBound.setText("0");}
        if("".equals(dUpperBound.getText().trim())){dUpperBound.setText("0");}
        if("".equals(dPoints.getText().trim())){dPoints.setText("0");}
        
        if("".equals(eLowerBound.getText().trim())){eLowerBound.setText("0");}
        if("".equals(eUpperBound.getText().trim())){eUpperBound.setText("0");}
        if("".equals(ePoints.getText().trim())){ePoints.setText("0");}
        
        if("".equals(fLowerBound.getText().trim())){fLowerBound.setText("0");}
        if("".equals(fUpperBound.getText().trim())){fUpperBound.setText("0");}
        if("".equals(fPoints.getText().trim())){fPoints.setText("0");}
        
        if("".equals(uLowerBound.getText().trim())){uLowerBound.setText("0");}
        if("".equals(uUpperBound.getText().trim())){uUpperBound.setText("0");}
        if("".equals(uPoints.getText().trim())){uPoints.setText("0");}
        return true;
    }
}