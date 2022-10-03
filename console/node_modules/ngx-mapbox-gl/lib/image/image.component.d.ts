import { EventEmitter, NgZone, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
import { MapService } from '../map/map.service';
import { MapImageData, MapImageOptions } from '../map/map.types';
export declare class ImageComponent implements OnInit, OnDestroy, OnChanges {
    private MapService;
    private zone;
    id: string;
    data?: MapImageData;
    options?: MapImageOptions;
    url?: string;
    error: EventEmitter<{
        status: number;
    }>;
    loaded: EventEmitter<void>;
    private isAdded;
    private isAdding;
    private sub;
    constructor(MapService: MapService, zone: NgZone);
    ngOnInit(): void;
    ngOnChanges(changes: SimpleChanges): void;
    ngOnDestroy(): void;
    private init;
}
