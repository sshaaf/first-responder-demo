import { AfterContentInit, ChangeDetectorRef, NgZone, OnDestroy, TemplateRef } from '@angular/core';
import { MapboxGeoJSONFeature } from 'mapbox-gl';
import { MapService } from '../map/map.service';
export declare class PointDirective {
}
export declare class ClusterPointDirective {
}
export declare class MarkersForClustersComponent implements OnDestroy, AfterContentInit {
    private MapService;
    private ChangeDetectorRef;
    private zone;
    source: string;
    pointTpl?: TemplateRef<any>;
    clusterPointTpl: TemplateRef<any>;
    clusterPoints: MapboxGeoJSONFeature[];
    layerId: string;
    private sub;
    constructor(MapService: MapService, ChangeDetectorRef: ChangeDetectorRef, zone: NgZone);
    ngAfterContentInit(): void;
    ngOnDestroy(): void;
    trackByClusterPoint(_index: number, clusterPoint: {
        id: number;
    }): number;
    private updateCluster;
}
