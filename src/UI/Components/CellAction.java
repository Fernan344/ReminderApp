/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI.Components;

import com.raven.table.TableCustom;
import com.raven.table.cell.TableCustomCell;
import com.raven.table.model.TableRowData;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author teval
 */
public class CellAction extends TableCustomCell {

    /**
     * Creates new form CellAction
     */
    public CellAction() {
        initComponents();
    }

    private void addEvent(TableCustom table, TableRowData data, int row){
        cmdEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(data.isEditing()){
                    table.cancelEditRowAt(row);
                    cmdEdit.setIcon(new ImageIcon(getClass().getResource("/Recursos/edit.png")));
                    redimensionTable(table, false);
                }else{
                    table.editRowAt(row);
                    cmdEdit.setIcon(new ImageIcon(getClass().getResource("/Recursos/cancel.png")));
                    redimensionTable(table, true);
                }
            }
        });
        cmdDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.deleteRowAt(getRow(), true);
            }
        });
    }
    
    private void checkIcon(TableRowData data, TableCustom table){
        if(data.isEditing()){
            cmdEdit.setIcon(new ImageIcon(getClass().getResource("/Recursos/cancel.png")));             
        }else{
            cmdEdit.setIcon(new ImageIcon(getClass().getResource("/Recursos/edit.png")));            
        }
    }
    
    private void redimensionTable(TableCustom table, boolean editMode){
        TableColumnModel columnModel = table.getColumnModel();
        if(editMode){
            columnModel.getColumn(0).setPreferredWidth(10);
            columnModel.getColumn(1).setPreferredWidth(150);
            columnModel.getColumn(2).setPreferredWidth(125);
            columnModel.getColumn(3).setPreferredWidth(100);
            columnModel.getColumn(4).setPreferredWidth(25);
            columnModel.getColumn(5).setPreferredWidth(75);
            columnModel.getColumn(6).setPreferredWidth(50);
        }else{
            columnModel.getColumn(0).setPreferredWidth(10);
            columnModel.getColumn(1).setPreferredWidth(150);
            columnModel.getColumn(2).setPreferredWidth(50);
            columnModel.getColumn(3).setPreferredWidth(25);
            columnModel.getColumn(4).setPreferredWidth(150);
            columnModel.getColumn(5).setPreferredWidth(85);
            columnModel.getColumn(6).setPreferredWidth(50);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdEdit = new javax.swing.JButton();
        cmdDelete = new javax.swing.JButton();

        cmdEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/edit.png"))); // NOI18N
        cmdEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditActionPerformed(evt);
            }
        });

        cmdDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/delete.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdDelete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdEdit)
                    .addComponent(cmdDelete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdEditActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdDelete;
    private javax.swing.JButton cmdEdit;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setData(Object o) {
        
    }

    @Override
    public Object getData() {
        return null;
    }

    @Override
    public Component createComponentCellRender(TableCustom table, TableRowData data, int row, int column) {
        CellAction cell = new CellAction();
        cell.checkIcon(data, table);
        cell.addEvent(table, data, row);
        return cell;
    }

    @Override
    public Component createComponentCellRenderOnEditor(TableCustom table, TableRowData data, int row, int column) {
        return null;
    }

    @Override
    public TableCustomCell createComponentCellEditor(TableCustom tc, TableRowData trd, Object o, int row, int column) {
        CellAction cell = new CellAction();
        cell.checkIcon(trd, tc);
        cell.addEvent(tc, trd, row);
        return cell;
    }
}