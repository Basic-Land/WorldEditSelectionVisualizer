package fr.mrmicky.worldeditselectionvisualizer.selection.shape.type;

import com.sk89q.worldedit.regions.Polygonal2DRegion;
import fr.mrmicky.worldeditselectionvisualizer.WorldEditSelectionVisualizer;
import fr.mrmicky.worldeditselectionvisualizer.compat.RegionAdapter;
import fr.mrmicky.worldeditselectionvisualizer.config.GlobalSelectionConfig;
import fr.mrmicky.worldeditselectionvisualizer.math.Vector3d;
import fr.mrmicky.worldeditselectionvisualizer.selection.SelectionPoints;
import fr.mrmicky.worldeditselectionvisualizer.selection.shape.ShapeProcessor;

import java.util.ArrayList;
import java.util.List;

public class Polygonal2DProcessor extends ShapeProcessor<Polygonal2DRegion> {

    public Polygonal2DProcessor(WorldEditSelectionVisualizer plugin) {
        super(Polygonal2DRegion.class, plugin);
    }

    @Override
    public void processSelection(SelectionPoints selection, Polygonal2DRegion region, RegionAdapter regionAdapter, GlobalSelectionConfig config) {
        double minY = regionAdapter.getMinimumPoint().getY();
        int height = region.getHeight();

        List<Vector3d> polygonalPoints = regionAdapter.getPolygonalPoints();
        List<Vector3d> bottomCorners = new ArrayList<>(polygonalPoints.size());

        for (Vector3d vec2d : polygonalPoints) {
            bottomCorners.add(vec2d.add(0.5, minY, 0.5));
        }

        createLinesFromBottom(selection, bottomCorners, height, config);
    }
}
