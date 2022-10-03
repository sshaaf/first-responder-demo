import { AfterContentInit, OnChanges, SimpleChanges } from '@angular/core';
import { MapService } from '../map/map.service';
import { ControlComponent } from './control.component';
export declare class ScaleControlDirective implements AfterContentInit, OnChanges {
    private MapService;
    private ControlComponent;
    maxWidth?: number;
    unit?: 'imperial' | 'metric' | 'nautical';
    constructor(MapService: MapService, ControlComponent: ControlComponent);
    ngOnChanges(changes: SimpleChanges): void;
    ngAfterContentInit(): void;
}
