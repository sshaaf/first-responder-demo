import { AfterContentInit } from '@angular/core';
import { MapService } from '../map/map.service';
import { ControlComponent } from './control.component';
export declare class AttributionControlDirective implements AfterContentInit {
    private MapService;
    private ControlComponent;
    compact?: boolean;
    customAttribution?: string | string[];
    constructor(MapService: MapService, ControlComponent: ControlComponent);
    ngAfterContentInit(): void;
}
